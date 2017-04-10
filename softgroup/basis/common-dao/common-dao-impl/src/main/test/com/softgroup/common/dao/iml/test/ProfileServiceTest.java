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
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Before
    public void init(){
        List<ProfileEntity> profileEntity = new ArrayList<>();

        ProfileEntity igor = new ProfileEntity(UUID.randomUUID().toString(),"Gregor");
        ProfileEntity mihalych = new ProfileEntity(UUID.randomUUID().toString(),"+380998063701");

        if (profileService.findByName(igor.getName()) != null)
            profileEntity.add(igor);

        if (profileService.findByName(mihalych.getName()) != null)
            profileEntity.add(mihalych);

        profileService.save(profileEntity);
    }

    @Test
    public void findByNameTest(){
        List<ProfileEntity> res = profileService.findByName("Igor");

        ProfileEntity igor = res.get(0);

        assertThat(igor.getStatus(), is("not active"));
        assertThat(igor.getCreateDateTime(), is(res.get(0).getUpdateDateTime()));
        assertThat(igor.getName(), is("Igor"));
        assertThat(igor.getPhoneNumber(), nullValue());

        res = profileService.findByName("Mihalych");

        ProfileEntity mihalych = res.get(0);

        assertThat(mihalych.getStatus(), is("not active"));
        assertThat(mihalych.getCreateDateTime(), is(mihalych.getUpdateDateTime()));
        assertThat(mihalych.getName(), is("Mihalych"));
        assertThat(mihalych.getPhoneNumber(), is("+380998063701"));

        mihalych.setStatus("allowed");
        profileService.save(mihalych);

        res = profileService.findByName("Mihalych");

        ProfileEntity new_michalych = res.get(0);
        assertThat(mihalych.getStatus(), is("allowed"));
        //assertThat(mihalych.getCreateDateTime(), not(res.get(0).getUpdateDateTime())); // todo crash test
        assertThat(mihalych.getName(), is("Mihalych"));
        assertThat(mihalych.getPhoneNumber(), is("+380998063701"));


    }


    @Test
    public void findByStatusTest(){
        List<ProfileEntity> res = profileService.findByStatus("allowed");

        assertThat(res.get(0).getName(), is("Mihalych"));


    }

}
