package com.softgroup.common.token.api;

/**
 * Created by anton on 15.03.17.
 */
public interface IToken {

    String createDeviceToken(String userId, String deviceId, Long locale) throws Exception;
    //String createTemporaryToken(String userId, String deviceId);
    //String getUserIdFromDeviceToken(String token);
    //String getPhoneNumberFromTemporaryToken(String token);
    //RoutedData getRoutingData(String token);

}
