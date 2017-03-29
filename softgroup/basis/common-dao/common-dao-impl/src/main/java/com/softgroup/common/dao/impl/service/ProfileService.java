package com.softgroup.common.dao.impl.service;


import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.repositories.ProfileRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by anton on 11.03.17.
 */
@Component
public class ProfileService extends BaseCRUDService<ProfileEntity, ProfileRepository> {

    public List<ProfileEntity> findByName(String name){
        return getRepository().findByName(name);
    }

    public List<ProfileEntity> findByStatus(String status){
        return getRepository().findByStatus(status);
    }

}
