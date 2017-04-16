package com.softgroup.common.protocol;

import java.io.Serializable;

/**
 * Created by anton on 18.03.17.
 */
public class RoutedData implements Serializable {
    private static final long serialVersionUID = 3930278156237773465L;

    private String deviceID;

    private String userID;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}