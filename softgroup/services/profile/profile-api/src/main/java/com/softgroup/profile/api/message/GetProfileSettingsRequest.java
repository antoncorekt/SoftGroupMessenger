package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.RequestData;

/**
 * Created by anton on 11.03.17.
 */
public class GetProfileSettingsRequest implements RequestData {
    private static final long serialVersionUID = 7579342973963784122L;

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
