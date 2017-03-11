package com.softgroup.profile.api.message;

import com.softgroup.common.protocol.ResponseData;

/**
 * Created by anton on 11.03.17.
 */
public class GetContactProfilesResponse implements ResponseData {
    private static final long serialVersionUID = 7182031214980471450L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
