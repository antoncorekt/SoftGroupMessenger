
package com.softgroup.common.dao.api.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by anton on 15.03.17.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -6048039879772600537L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    public BaseEntity(){}

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