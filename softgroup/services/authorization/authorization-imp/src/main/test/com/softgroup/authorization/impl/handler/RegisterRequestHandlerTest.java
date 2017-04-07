package com.softgroup.authorization.impl.handler;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.impl.session.SessionData;
import com.softgroup.authorization.impl.session.SessionService;
import com.softgroup.authorization.impl.sms.SmsSender;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.protocol.utils.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 07.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterRequestHandlerTest {

    @InjectMocks
    private RegisterRequestHandler requestHandler;

    @Spy
    private SessionService serviceSession = Mockito.mock(SessionService.class);

    @Spy
    private SmsSender smsSender = Mockito.mock(SmsSender.class);

    @Before
    public void init(){
        when(serviceSession.addSession("+380999014806","testDeviceId","ru"))
                .thenReturn(new SessionData("+380999014806","testDeviceId","ru"));

    }

    @Test
    public void getName() throws Exception {
        assertThat(requestHandler.getName(), is("register"));
    }

    @Test
    public void handleWork() throws Exception {
        Request<RegisterRequest> request = new Request<>();

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setDeviceID("testDeviceId");
        registerRequest.setLocaleCode("ru");
        registerRequest.setPhoneNumber("+380999014806");

        request.setData(registerRequest);
        request.setRoutedData(new RoutedData("testDeviceId","testUserId"));
        request.setHeader(new ActionHeader("uuid",
                "originUuid",
                "register",
                "authorization",
                "1.1"));

        Response<RegisterResponse> response = requestHandler.handleWork(request);

        assertThat(response.getStatus().getHttpStatus(),is(HttpStatus.OK));

        assertThat(response.getHeader().getCommand(), is("register"));
        assertThat(response.getHeader().getUuid(), is("originUuid"));
        assertThat(response.getHeader().getOriginUuid(), is("uuid"));
        assertThat(response.getHeader().getType(), is("authorization"));

        assertThat(response.getData().getAuthCode().length(), is(SessionData.getCountNumInCode()));
        assertThat(Integer.parseInt(response.getData().getAuthCode()), is(Integer.class));

        assertThat(response.getData().getRegistraionTimeOut(), is(SessionService.getLiveSession()));
        assertThat(response.getData().getRegistrationID(), is(String.class));
    }

}