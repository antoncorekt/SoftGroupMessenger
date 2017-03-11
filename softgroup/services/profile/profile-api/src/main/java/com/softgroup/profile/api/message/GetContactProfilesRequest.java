package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.RequestData;

/**
 * Created by anton on 11.03.17.
 */
public class GetContactProfilesRequest implements RequestData {
    private static final long serialVersionUID = -4833252146694364717L;

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
