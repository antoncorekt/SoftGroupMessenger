package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.RequestData;

/**
 * Created by anton on 11.03.17.
 */
public class SetProfileSettingsRequest implements RequestData {
    private static final long serialVersionUID = -3668172891070371522L;

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
