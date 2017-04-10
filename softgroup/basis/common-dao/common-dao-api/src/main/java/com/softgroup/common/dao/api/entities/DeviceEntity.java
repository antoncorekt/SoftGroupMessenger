package com.softgroup.common.dao.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by anton on 29.03.17.
 */
@Entity
@Table(name = "devices")
public class DeviceEntity extends BaseEntity {
    private static final long serialVersionUID = -7018253653281101442L;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "device_id")
    private String deviceID;

    @Column(name = "confirmation_time")
    private Long confirmaionTime;

    public DeviceEntity(){}

    public DeviceEntity(String id, String userID, String deviceID, Long confirmaionTime) {
        super(id);
        this.userID = userID;
        this.deviceID = deviceID;
        this.confirmaionTime = confirmaionTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Long getConfirmaionTime() {
        return confirmaionTime;
    }

    public void setConfirmaionTime(Long confirmaionTime) {
        this.confirmaionTime = confirmaionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntity that = (DeviceEntity) o;

        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (deviceID != null ? !deviceID.equals(that.deviceID) : that.deviceID != null) return false;
        return confirmaionTime != null ? confirmaionTime.equals(that.confirmaionTime) : that.confirmaionTime == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (deviceID != null ? deviceID.hashCode() : 0);
        result = 31 * result + (confirmaionTime != null ? confirmaionTime.hashCode() : 0);
        return result;
    }
}
