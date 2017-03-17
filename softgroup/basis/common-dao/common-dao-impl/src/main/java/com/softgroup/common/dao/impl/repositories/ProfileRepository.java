package com.softgroup.common.dao.impl.repositories;


import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.api.repositories.IRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by anton on 11.03.17.
 */
@Component
public interface ProfileRepository extends IRepository<ProfileEntity, String>{

    List<ProfileEntity> findByName(String s);

    List<ProfileEntity> findByStatus(String s);
}
