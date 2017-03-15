package com.softgroup.common.dao.impl.repositories;

import com.softgroup.common.dao.api.entities.TokenEntity;
import com.softgroup.common.dao.api.repositories.IRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by anton on 15.03.17.
 */
@Component
public interface TokenRepository extends IRepository<TokenEntity, String> {
    //List<TokenEntity> findByUserIDAndDeviceID(String userID, String deviceID);

    List<TokenEntity> findByDeviceID(String deviceID);
}
