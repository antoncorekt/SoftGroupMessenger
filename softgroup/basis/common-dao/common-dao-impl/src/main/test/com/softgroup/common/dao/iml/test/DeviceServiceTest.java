package com.softgroup.common.dao.iml.test;

import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.dao.impl.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by anton on 03.04.17.
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoImplAppCfg.class})
@ActiveProfiles("test")
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void test(){

        deviceService.save(new DeviceEntity(UUID.randomUUID().toString(),
                "USERid50011",
                "devID500",
                14144999L));

        DeviceEntity deviceEntity = deviceService.findByUserIDAndDeviceID("USERid50011","devID500");

        System.out.println(deviceEntity.getConfirmaionTime());

    }

}
