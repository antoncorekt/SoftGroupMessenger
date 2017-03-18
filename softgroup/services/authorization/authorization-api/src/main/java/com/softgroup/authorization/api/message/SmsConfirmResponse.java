package com.softgroup.authorization.api.message;

import com.softgroup.common.protocol.ResponseData;

/**
 * Created by anton on 28.02.17.
 */
public class SmsConfirmResponse implements ResponseData {
    private static final long serialVersionUID = -884653831847610223L;

    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
