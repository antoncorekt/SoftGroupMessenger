package com.softgroup.common.dao.api.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by anton on 15.03.17.
 */

@Entity
@Table(name = "token")
public class TokenEntity implements IEntity {
    private static final long serialVersionUID = -8386138607819184655L;

    @Id
    private String id;

    @Column(name = "device_id")
    private String deviceID;

    @Column(name = "creations_time")
    private Long creationsTime;

    @Column(name = "user_id")
    private Long userID;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Long getCreationsTime() {
        return creationsTime;
    }

    public void setCreationsTime(Long creationsTime) {
        this.creationsTime = creationsTime;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenEntity that = (TokenEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (deviceID != null ? !deviceID.equals(that.deviceID) : that.deviceID != null) return false;
        if (creationsTime != null ? !creationsTime.equals(that.creationsTime) : that.creationsTime != null)
            return false;
        return userID != null ? userID.equals(that.userID) : that.userID == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (deviceID != null ? deviceID.hashCode() : 0);
        result = 31 * result + (creationsTime != null ? creationsTime.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        return result;
    }
}
