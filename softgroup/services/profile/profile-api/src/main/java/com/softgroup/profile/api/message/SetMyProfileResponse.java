package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.ResponseData;

/**
 * Created by anton on 11.03.17.
 */
public class SetMyProfileResponse implements ResponseData {
    private static final long serialVersionUID = 6620656643645510983L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
