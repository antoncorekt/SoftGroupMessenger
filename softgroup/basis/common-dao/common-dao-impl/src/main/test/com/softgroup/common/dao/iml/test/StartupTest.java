package com.softgroup.common.dao.iml.test;

import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.dao.impl.repositories.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by anton on 11.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoImplAppCfg.class})
public class StartupTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void test(){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId("id");
        profileEntity.setName("test_name");

        profileEntity = profileRepository.save(profileEntity);
    }


}
