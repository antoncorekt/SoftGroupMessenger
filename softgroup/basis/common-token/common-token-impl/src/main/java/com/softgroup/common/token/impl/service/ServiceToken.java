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
    public String createDeviceToken(String userId, String deviceId) throws Exception {

        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("userID", userId);
            claims.setClaim("deviceID", deviceId);
            claims.setClaim("type", TOKEN_DEVICE);

            return encrypt(claims);
        } catch (Exception e) {
            throw new Exception("Error create device token", new TokenException());
        }

    }

    @Override
    public String createSessionToken(String userId, String deviceId) throws Exception {
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setClaim("userID", userId);
            claims.setClaim("deviceID", deviceId);
            claims.setClaim("type", TOKEN_SESSION);
            claims.setExpirationTimeMinutesInTheFuture(15);

            return encrypt(claims);
        } catch (Exception e) {
            throw new Exception("Error session token", new TokenException());
        }
    }

    @Override
    public RoutedData getRoutedData(String token) throws Exception {
        try {
            JwtClaims claims = getClaimsFromToken(token);
            if (!claims.getStringClaimValue("type").equals(TOKEN_SESSION))
                throw new Exception("Not TOKEN_SESSION", new TokenException());
            if (claims.getExpirationTime().getValueInMillis() < System.currentTimeMillis())
                throw new Exception("Token time error", new TokenException());
            return new RoutedData(claims.getStringClaimValue("deviceID"),
                    claims.getStringClaimValue("userID"));
        } catch (Exception e) {
            throw new Exception("Error create routed data", new TokenException());
        }
    }


    private String encrypt(JwtClaims claims) throws Exception{
        try {
            JsonWebEncryption encryption = getEncryption();
            encryption.setPayload(claims.toJson());
            return encryption.getCompactSerialization();
        } catch (Exception e) {
            throw new Exception("Error encrypt token", new TokenException());
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
            throw new Exception("Error get claims token", new TokenException());
        }
    }
}