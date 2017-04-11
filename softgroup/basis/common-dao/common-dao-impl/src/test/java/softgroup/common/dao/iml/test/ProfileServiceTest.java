package com.softgroup.common.dao.iml.test;

import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.dao.impl.service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by anton on 15.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoImplAppCfg.class})
@ActiveProfiles("test")
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    private String phoneNumber;

    @Before
    public void init(){
        phoneNumber = UUID.randomUUID().toString();

        profileService.save(new ProfileEntity(UUID.randomUUID().toString(),
                phoneNumber));
    }


    @Test
    public void findByPhoneNumberTest(){
        ProfileEntity res = profileService.findByPhoneNumber(phoneNumber);

        assertThat(res.getPhoneNumber(), is(phoneNumber));

        res = profileService.findByPhoneNumber("fakeNumber");

        assertThat(res, nullValue());


    }

}
