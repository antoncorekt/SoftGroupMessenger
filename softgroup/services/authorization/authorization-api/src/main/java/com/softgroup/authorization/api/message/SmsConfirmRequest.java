package com.softgroup.authorization.api.message;

import com.softgroup.common.protocol.RequestData;

/**
 * Created by anton on 28.03.17.
 */
public class SmsConfirmRequest implements RequestData {
    private static final long serialVersionUID = -2695624283053781717L;

    private String authCode;
    private String registrationID;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }
}
