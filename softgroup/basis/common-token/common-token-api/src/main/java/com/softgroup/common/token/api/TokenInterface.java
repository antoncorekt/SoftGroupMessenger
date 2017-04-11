package com.softgroup.common.token.api;

import com.softgroup.common.protocol.RoutedData;

/**
 * Created by anton on 18.03.17.
 */
public interface TokenInterface {
    String createDeviceToken(String userId, String deviceId) throws Exception;
    String createSessionToken(String userId, String deviceId) throws Exception;

    RoutedData getRoutedData(String token) throws Exception;
}
