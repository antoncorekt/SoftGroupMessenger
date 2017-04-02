package com.softgroup.common.dao.impl.repositories;


import com.softgroup.common.dao.api.entities.BaseEntity;
import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.api.repository.BaseRerository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by anton on 11.03.17.
 */
@Repository
public interface ProfileRepository extends BaseRerository<ProfileEntity> {

    List<ProfileEntity> findByName(String s);

    List<ProfileEntity> findByStatus(String s);
}
