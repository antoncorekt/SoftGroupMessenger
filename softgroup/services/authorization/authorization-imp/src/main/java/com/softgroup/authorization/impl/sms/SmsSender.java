package com.softgroup.authorization.impl.sms;

import org.springframework.stereotype.Component;

/**
 * Created by anton on 29.03.17.
 */
@Component
public class SmsSender {

    private String number;
    private String authCode;


    public void send(){

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}