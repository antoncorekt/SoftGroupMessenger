package com.softgroup.common.dao.impl.service;

import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.impl.repositories.DeviceRepository;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 02.04.17.
 */
@Component
public class DeviceService extends BaseCRUDService<DeviceEntity, DeviceRepository> {

    public DeviceEntity findByUserIDAndDeviceID(String userID, String deviceID){
        return getRepository().findByUserIDAndDeviceID(userID,deviceID);
    }
}
