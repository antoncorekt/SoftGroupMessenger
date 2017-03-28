package com.softgroup.common.token.impl.test;

import com.softgroup.common.token.impl.configurations.TokenCfg;

import com.softgroup.common.token.impl.service.ServiceToken;
import org.hamcrest.CoreMatchers;
import org.jose4j.jwt.JwtClaims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by anton on 15.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TokenCfg.class})
public class ServiceTokenTest {

    @Autowired
    private ServiceToken serviceToken;

    @Test
    public void createDeviceTokenTest(){

        try {
            String s = serviceToken.createDeviceToken("testID", "testDevice");

            JwtClaims res = serviceToken.getClaimsFromToken(s);

            assertThat(res.getClaimsMap().get("userID"), CoreMatchers.<Object>is("testID" ));
            assertThat(res.getClaimsMap().get("deviceID"), CoreMatchers.<Object>is("testDevice" ));
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

}

