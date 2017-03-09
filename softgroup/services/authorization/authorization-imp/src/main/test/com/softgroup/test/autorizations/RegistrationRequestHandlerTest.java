package com.softgroup.test.autorizations;

import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.impl.handler.RegistrationRequestHandler;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertThat;

/**
 * Created by anton on 28.02.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistrationRequestHandlerTest {

    @InjectMocks
    private RegistrationRequestHandler registrationRequestHandler;

    private Request<RegisterRequest> request = new Request<>();

    @Before
    public void init(){
        ActionHeader actionHeader = new ActionHeader();
        actionHeader.setUuid(UUID.randomUUID().toString());
        actionHeader.setType("authorization");
        actionHeader.setCommand("register");

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setDeviceId("devise_id");
        registerRequest.setLocaleCode("uk_UA");
        registerRequest.setPhoneNumber("+3809912345601");

        request.setHeader(actionHeader);
        request.setData(registerRequest);
    }

    @org.junit.Test
    public void handleWorkTest(){
        Response<RegisterResponse> res = registrationRequestHandler.handleWork(request);

        assertThat(res.getStatus().getCode(),is(200));
        assertThat(res.getStatus().getMessage(),is("OK"));
        assertThat(res.getHeader().getType(),is("authorization"));
        assertThat(res.getHeader().getCommand(),is("register"));
        assertThat(res.getData().getAuthCode(),any(String.class));
        assertThat(res.getData().getRegistrationRequestUuid(),any(String.class));
        assertThat(res.getData().getRegistrationTimeoutSec(),is(10));

        assertThat(registrationRequestHandler.handleWork(null).getStatus().getCode(),is(404));
    }



    @Test
    public void testGetName(){

        assertThat(registrationRequestHandler.getName(), is("register"));
    }


}
