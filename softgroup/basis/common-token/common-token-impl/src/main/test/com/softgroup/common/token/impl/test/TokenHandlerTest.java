package com.softgroup.common.token.impl.test;

import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.token.impl.configurations.TokenCfg;
import com.softgroup.common.token.impl.handlerimpl.TokenHandler;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by anton on 15.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TokenCfg.class})
public class TokenHandlerTest {

    @Autowired
    private TokenHandler tokenHandler;

    @Test
    public void createDeviceTokenTest(){

        try {
            String s = tokenHandler.createDeviceToken("testID", "testDevice", 123456L);

            JwtClaims res = tokenHandler.getClaimsFromToken(s);

            assertThat(res.getClaimsMap().get("userID"), is("testID" ));
            assertThat(res.getClaimsMap().get("deviceID"), is("testDevice" ));
            assertThat(res.getClaimsMap().get("localeCode"), is(123456L ));
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
