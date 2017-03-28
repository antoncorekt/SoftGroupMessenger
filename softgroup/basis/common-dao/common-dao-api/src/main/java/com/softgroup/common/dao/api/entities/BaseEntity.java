package com.softgroup.common.dao.api.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by anton on 15.03.17.
 */

public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
