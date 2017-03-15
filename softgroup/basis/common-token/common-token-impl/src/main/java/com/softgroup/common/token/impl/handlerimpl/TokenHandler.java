package com.softgroup.common.token.impl.handlerimpl;

import com.softgroup.common.dao.api.entities.TokenEntity;
import com.softgroup.common.dao.impl.service.TokenService;
import com.softgroup.common.token.api.IToken;
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
public class TokenHandler implements IToken {

    private AesKey key = new AesKey(ByteUtil.randomBytes(16));

    @Autowired
    private TokenService tokenService;


    @Override
    public String createDeviceToken(String userId, String deviceId, Long locale) throws Exception {

        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("test_user_id", userId);
            claims.setClaim("test_device_id", deviceId);
            claims.setClaim("test_locale_code", locale);

            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setId(userId);
            tokenEntity.setDeviceID(deviceId);
            tokenEntity.setCreationsTime(locale);

            //tokenService.save(new TokenEntity(phoneNumber, deviceId, claims.getIssuedAt()));
            tokenService.save(tokenEntity);
            return encrypt(claims);
        } catch (Exception e) {
            throw new Exception("Error encrypt " + e.toString());
        }

    }



    private String encrypt(JwtClaims claims) throws Exception{
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setPayload(claims.toJson());
            return encryption.getCompactSerialization();
        } catch (Exception e) {
            throw new Exception("Error encrypt " + e.toString());
        }

    }

    private JsonWebEncryption getEncryption() {
        JsonWebEncryption encryption = new JsonWebEncryption();
        encryption.setKey(key);
        encryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        encryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        return encryption;
    }

    private JwtClaims getClaimsFromToken(String token) throws Exception {
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setCompactSerialization(token);
            return JwtClaims.parse(encryption.getPayload());
        } catch (Exception e) {
            throw new Exception("Error encrypt " + e.toString());
        }
    }
}
