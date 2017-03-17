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


    /*  DeviceEntity
        id
        profile_id
        last_auth
        local_code
        device_id
     */

    //         Token
    //     /          \
    /* DeviceToken  SessionToken
         user_id
         device_id
         type  --  device or session

             + create_time
             + expirations
     */
}
