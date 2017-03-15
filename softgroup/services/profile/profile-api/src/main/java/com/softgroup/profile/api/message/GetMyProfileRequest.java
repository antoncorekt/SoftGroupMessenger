package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.RequestData;

/**
 * Created by anton on 11.03.17.
 */
public class GetMyProfileRequest implements RequestData {
    private static final long serialVersionUID = -3940646051213242842L;

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
