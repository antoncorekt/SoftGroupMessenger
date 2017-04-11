package com.softgroup.common.dao.iml.test;

import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.dao.impl.service.DeviceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anton on 03.04.17.
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoImplAppCfg.class})
@ActiveProfiles("test")
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    private String userID, deviceID;
    private long confTime;

    @Before
    public void init(){
        userID = "userID";
        deviceID = "deviceID_"+UUID.randomUUID().toString();
        confTime = Math.round(Math.random()*100000);

        deviceService.save(new DeviceEntity(UUID.randomUUID().toString(),
                userID,
                deviceID,
                confTime));
    }



    @Test
    public void findByUserIDandDeviceIDtest(){
        DeviceEntity deviceEntity = deviceService.findByUserIDAndDeviceID(userID,deviceID);

        assertThat(deviceEntity.getUserID(),is(userID));
        assertThat(deviceEntity.getDeviceID(),is(deviceID));
        assertThat(deviceEntity.getConfirmaionTime(),is(confTime));


        deviceEntity = deviceService.findByUserIDAndDeviceID(userID,"fakeDevice");

        assertThat(deviceEntity,nullValue());
    }

}
