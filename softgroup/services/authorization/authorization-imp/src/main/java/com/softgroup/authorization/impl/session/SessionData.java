package com.softgroup.authorization.impl.session;

import javax.crypto.KeyGenerator;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by anton on 29.03.17.
 */
public class SessionData implements Serializable {
    private static final long serialVersionUID = -29781325728291255L;

    private String phoneNumber;
    private String deviceId;
    private String locale;

    private String uuid;
    private long creationTime;
    private String authCode;
    private int timeOut;

    private static final Integer TIME_OUT = 1000;

    public SessionData(String phoneNumber, String deviceId, String locale) {
        this.phoneNumber = phoneNumber;
        this.deviceId = deviceId;
        this.locale = locale;
        this.uuid = UUID.randomUUID().toString();
        this.creationTime = System.currentTimeMillis();
        this.authCode = createAuthCode();
        this.timeOut = TIME_OUT;
    }

    public String createAuthCode(){

        return UUID.randomUUID().toString();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
