package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.RequestData;

/**
 * Created by anton on 11.03.17.
 */
public class SetMyProfileRequest implements RequestData {
    private static final long serialVersionUID = -3479391449795900015L;

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
