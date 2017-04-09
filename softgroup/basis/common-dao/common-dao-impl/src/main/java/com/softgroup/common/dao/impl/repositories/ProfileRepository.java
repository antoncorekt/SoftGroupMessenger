package com.softgroup.common.dao.impl.repositories;


import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.api.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by anton on 11.03.17.
 */
@Repository
public interface ProfileRepository extends BaseRepository<ProfileEntity> {

    ProfileEntity findByPhoneNumber(String s);
}
