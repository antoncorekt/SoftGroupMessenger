package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.ResponseData;

/**
 * Created by anton on 11.03.17.
 */
public class GetProfileSettingsResponse implements ResponseData {
    private static final long serialVersionUID = -3218706613545361861L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
