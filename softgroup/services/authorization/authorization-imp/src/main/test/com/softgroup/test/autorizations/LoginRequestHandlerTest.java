package com.softgroup.test.autorizations;

import com.softgroup.authorization.api.message.*;
import com.softgroup.authorization.impl.config.AuthConfig;
import com.softgroup.authorization.impl.handler.LoginRequestHandler;
import com.softgroup.authorization.impl.handler.RegisterRequestHandler;
import com.softgroup.common.dao.impl.configurations.DaoImplAppCfg;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.token.impl.configurations.TokenCfg;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;

/**
 * Created by anton on 29.03.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AuthConfig.class, TokenCfg.class, DaoImplAppCfg.class})
public class LoginRequestHandlerTest {

    @Autowired
    private RegisterRequestHandler registerRequestHandler;

    @Autowired
    private LoginRequestHandler loginRequestHandler;

    @Before
    public void init(){
      //  registerRequestHandler = Mockito.mock(RegisterRequestHandler.class, Mockito.CALLS_REAL_METHODS);

    }

    @Test
    public void test(){
        Request<RegisterRequest> request1 = new Request<>();

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPhoneNumber("+380631234567");
        registerRequest.setLocaleCode("uk_UA");
        registerRequest.setDeviceID("dev_id");

        request1.setHeader(new ActionHeader("uuid1", null, "register", "authorization", "HTTP/1.1"));
        request1.setData(registerRequest);



        Response<RegisterResponse> response1 = registerRequestHandler.handleWork(request1);

        System.out.println(response1.getHeader().getCommand());

    }
}
