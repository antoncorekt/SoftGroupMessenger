package com.softgroup.common.token.impl.service;

import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.token.api.TokenInterface;
import com.softgroup.common.token.api.TokenException;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 15.03.17.
 */

@Component
public class ServiceToken implements TokenInterface {

    private AesKey key = new AesKey(ByteUtil.randomBytes(16));

    private static final String TOKEN_DEVICE = "deviceToken";
    private static final String TOKEN_SESSION = "sessionToken";

    @Override
    public String createDeviceToken(String userId, String deviceId) throws TokenException {

        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("userID", userId);
            claims.setClaim("deviceID", deviceId);
            claims.setClaim("type", TOKEN_DEVICE);

            return encrypt(claims);
        } catch (Exception e) {
            throw new TokenException(e.getLocalizedMessage());
        }

    }

    @Override
    public String createSessionToken(String userId, String deviceId) throws TokenException {
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("userID", userId);
            claims.setClaim("deviceID", deviceId);
            claims.setClaim("type", TOKEN_SESSION);
            claims.setExpirationTimeMinutesInTheFuture(15);

            return encrypt(claims);
        } catch (Exception e) {
            throw new TokenException(e.getLocalizedMessage());
        }
    }

    @Override
    public RoutedData getRoutedData(String token) throws TokenException {
        try {
            JwtClaims claims = getClaimsFromToken(token);
            if (!claims.getStringClaimValue("type").equals(TOKEN_SESSION))
                throw new TokenException("Error not session token");
            if (claims.getExpirationTime().getValueInMillis() < System.currentTimeMillis())
                throw new TokenException("Token time error");
            return new RoutedData(claims.getStringClaimValue("deviceID"),
                    claims.getStringClaimValue("userID"));
        } catch (Exception e) {
            throw new TokenException(e.getLocalizedMessage());
        }
    }


    private String encrypt(JwtClaims claims) throws TokenException{
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setPayload(claims.toJson());
            return encryption.getCompactSerialization();
        } catch (Exception e) {
            throw new TokenException("Error token");
        }

    }

    private JsonWebEncryption getEncryption() {
        JsonWebEncryption encryption = new JsonWebEncryption();
        encryption.setKey(key);
        encryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        encryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        return encryption;
    }

    public JwtClaims getClaimsFromToken(String token) throws TokenException {
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setCompactSerialization(token);
            return JwtClaims.parse(encryption.getPayload());
        } catch (Exception e) {
            throw new TokenException(e.getLocalizedMessage());
        }
    }
}