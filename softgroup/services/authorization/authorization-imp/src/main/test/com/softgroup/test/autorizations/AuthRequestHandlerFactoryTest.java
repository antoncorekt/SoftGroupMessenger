package com.softgroup.test.autorizations;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.authorization.impl.factory.AuthorizationRequestHandlerFactory;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.RequestData;
import com.softgroup.common.router.api.Handler;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by anton on 18.03.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthRequestHandlerFactoryTest {

    @InjectMocks
    private AuthorizationRequestHandlerFactory authorizationRequestHandlerFactory;

    @InjectMocks
    private Request<LoginRequest> login;

    @Spy
    private List<Handler> handlers = new ArrayList<>();

    @Before
    public void init(){
        ActionHeader actionHeader = new ActionHeader();
        actionHeader.setUuid(UUID.randomUUID().toString());
        actionHeader.setType("authorization");
        actionHeader.setCommand("register");

        login.setHeader(actionHeader);

        authorizationRequestHandlerFactory.init();

    }


    @Test
    public void test(){
        assertThat(authorizationRequestHandlerFactory.getKey(login),is("register"));
    }

}
