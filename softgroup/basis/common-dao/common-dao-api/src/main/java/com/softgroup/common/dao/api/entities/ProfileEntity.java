package com.softgroup.common.dao.api.entities;

//import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;


/**
 * Created by anton on 11.03.17.
 */
@Entity
@Table(name = "profiles")
public class ProfileEntity implements IEntity {
    private static final long serialVersionUID = -4280254323092119941L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "create_date_time")
    private Long createDateTime;

    @Column(name = "update_date_time")
    private Long updateDateTime;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;


    public ProfileEntity() {
        this.id = UUID.randomUUID().toString();
        createDateTime = updateDateTime = Instant.now().getEpochSecond();
        this.status = "not active";
    }

    public ProfileEntity(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        createDateTime = updateDateTime = Instant.now().getEpochSecond();
        this.status = "not active";
    }

    public ProfileEntity(String phoneNumber, String name) {
        this.id = UUID.randomUUID().toString();
        this.phoneNumber = phoneNumber;
        this.name = name;
        createDateTime = updateDateTime = Instant.now().getEpochSecond();
        this.status = "not active";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        updateDateTime = Instant.now().getEpochSecond();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateDateTime = Instant.now().getEpochSecond();
    }

    public Long getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Long createDateTime) {
        this.createDateTime = createDateTime;
        updateDateTime = Instant.now().getEpochSecond();
    }

    public Long getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Long updateDateTime) {
        this.updateDateTime = updateDateTime;
        updateDateTime = Instant.now().getEpochSecond();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateDateTime = Instant.now().getEpochSecond();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        updateDateTime = Instant.now().getEpochSecond();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileEntity that = (ProfileEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (createDateTime != null ? !createDateTime.equals(that.createDateTime) : that.createDateTime != null)
            return false;
        if (updateDateTime != null ? !updateDateTime.equals(that.updateDateTime) : that.updateDateTime != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        result = 31 * result + (updateDateTime != null ? updateDateTime.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
