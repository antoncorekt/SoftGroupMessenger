package com.softgroup.test.autorizations;

import com.softgroup.authorization.api.message.*;
import com.softgroup.authorization.impl.handler.LoginRequestHandler;
import com.softgroup.authorization.impl.handler.RegisterRequestHandler;
import com.softgroup.authorization.impl.handler.SmsConfirmHandler;
import com.softgroup.authorization.impl.session.ServiceSession;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.token.impl.service.ServiceToken;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;

/**
 * Created by anton on 29.03.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginRequestHandlerTest {

    @InjectMocks
    private RegisterRequestHandler registerRequestHandler;

    @InjectMocks
    private SmsConfirmHandler smsConfirmHandler;

    @InjectMocks
    private LoginRequestHandler loginRequestHandler;

    @InjectMocks
    private ServiceToken serviceToken;

    @Spy
    private ServiceSession serviceSession = new ServiceSession();

    @Before
    public void init(){
        registerRequestHandler = Mockito.mock(RegisterRequestHandler.class, Mockito.CALLS_REAL_METHODS);
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


    }
}
