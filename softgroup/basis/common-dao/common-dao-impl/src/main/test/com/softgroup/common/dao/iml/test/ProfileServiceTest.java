package com.softgroup.common.dao.iml.test;

import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.dao.impl.service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by anton on 15.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoImplAppCfg.class})
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    private ProfileEntity Igor=new ProfileEntity(),mihalych=new ProfileEntity();
    @Before
    public void init(){
        List<ProfileEntity> profileEntity = new ArrayList<>();

        Igor = new ProfileEntity(UUID.randomUUID().toString(),"+545445454545");
        mihalych = new ProfileEntity(UUID.randomUUID().toString(),"+380998063701");

        if (profileService.findByPhoneNumber(Igor.getPhoneNumber()) != null)
            profileEntity.add(Igor);

        if (profileService.findByPhoneNumber(mihalych.getPhoneNumber()) != null)
            profileEntity.add(mihalych);

        profileService.save(profileEntity);
    }

    @Test
    public void findByPhone(){
        ProfileEntity igor = profileService.findByPhoneNumber(Igor.getPhoneNumber());


        assertThat(igor.getPhoneNumber(), is("+545445454545"));

    }


}
