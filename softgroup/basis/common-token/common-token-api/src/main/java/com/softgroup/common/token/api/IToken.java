package com.softgroup.common.token.api;

import com.softgroup.common.protocol.RoutedData;

/**
 * Created by anton on 15.03.17.
 */
public interface IToken {

    String createDeviceToken(String userId, String deviceId, String locale);
    String createTemporaryToken(String userId, String deviceId);
    String getUserIdFromDeviceToken(String token);
    String getPhoneNumberFromTemporaryToken(String token);
    RoutedData getRoutingData(String token);

}
