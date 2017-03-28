package com.softgroup.common.token.api;

/**
 * Created by anton on 18.03.17.
 */
public interface TokenInterface {
    String createDeviceToken(String userId, String deviceId) throws TokenExceptions;
    String createSessionToken(String userId, String deviceId) throws TokenExceptions;
}
