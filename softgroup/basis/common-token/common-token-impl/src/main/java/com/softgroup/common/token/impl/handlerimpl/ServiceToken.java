package com.softgroup.common.token.impl.handlerimpl;

import com.softgroup.common.dao.api.entities.TokenEntity;
import com.softgroup.common.dao.impl.service.TokenService;
import com.softgroup.common.token.api.IToken;
import com.softgroup.common.token.api.TokenExceptions;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 15.03.17.
 */

@Component
public class ServiceToken implements IToken {

    private AesKey key = new AesKey(ByteUtil.randomBytes(16));

    private static final String TOKEN_DEVICE = "deviceToken";
    private static final String TOKEN_SESSION = "temporaryToken";

    @Override
    public String createDeviceToken(String userId, String deviceId) throws TokenExceptions {

        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("userID", userId);
            claims.setClaim("deviceID", deviceId);
            claims.setClaim("type", TOKEN_DEVICE);

            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setId(userId);
            tokenEntity.setDeviceID(deviceId);


            return encrypt(claims);
        } catch (Exception e) {
            throw new TokenExceptions("Error token " + e.toString());
        }

    }

    @Override
    public String createSessionToken(String userId, String deviceId) throws TokenExceptions {
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("userID", userId);
            claims.setClaim("deviceID", deviceId);
            claims.setClaim("type", TOKEN_SESSION);
            claims.setExpirationTimeMinutesInTheFuture(15);

            return encrypt(claims);
        } catch (Exception e) {
            throw new TokenExceptions("Error token " + e.toString());
        }
    }


    private String encrypt(JwtClaims claims) throws Exception{
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setPayload(claims.toJson());
            return encryption.getCompactSerialization();
        } catch (Exception e) {
            throw new TokenExceptions("Error token " + e.toString());
        }

    }

    private JsonWebEncryption getEncryption() {
        JsonWebEncryption encryption = new JsonWebEncryption();
        encryption.setKey(key);
        encryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        encryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        return encryption;
    }

    public JwtClaims getClaimsFromToken(String token) throws Exception {
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setCompactSerialization(token);
            return JwtClaims.parse(encryption.getPayload());
        } catch (Exception e) {
            throw new TokenExceptions("Error token " + e.toString());
        }
    }
}
