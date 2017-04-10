package com.softgroup.common.dao.impl.repositories;

import com.softgroup.common.dao.api.entities.*;
import com.softgroup.common.dao.api.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Created by anton on 02.04.17.
 */
@Repository
public interface DeviceRepository extends BaseRerository<DeviceEntity> {
    DeviceEntity findByUserIDAndDeviceID(String userID, String deviceID);
}
