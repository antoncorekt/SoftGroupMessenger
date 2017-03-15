package com.softgroup.common.dao.iml.test;

import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.dao.impl.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by anton on 15.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoImplAppCfg.class})
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @PostConstruct
    public void init(){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId("id");
        profileEntity.setName("test_name");

        profileService.save(profileEntity);
    }

    @Test
    public void findBynameTest(){
        List<ProfileEntity> profileEntity = profileService.findByName("test_name");


    }

}
