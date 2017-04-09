package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.impl.service.DeviceService;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.protocol.utils.ActionHeaderBuilder;
import com.softgroup.common.protocol.utils.HttpStatus;
import com.softgroup.common.token.api.TokenException;
import com.softgroup.common.token.impl.service.TokenService;
import org.jose4j.jwt.JwtClaims;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by anton on 07.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginRequestHandlerTest {
    private static Logger logger = LoggerFactory.getLogger(LoginRequestHandlerTest.class);

    @InjectMocks
    private LoginRequestHandler loginRequestHandler;

    @InjectMocks
    private TokenService tokenService;

    @Spy
    private TokenService serviceToken = Mockito.mock(TokenService.class);

    @Spy
    private DeviceService deviceService = Mockito.mock(DeviceService.class);

    private Request<LoginRequest> loginRequest;
    private String deviceToken;

    @Before
    public void init() throws Exception{
        loginRequest = new Request<>();

        // original device token
        deviceToken = tokenService.createDeviceToken("testUserId","testDeviceId");

        JwtClaims jwtClaims = tokenService.getClaimsFromToken(deviceToken);

        when(serviceToken.getClaimsFromToken(deviceToken)).
                thenReturn(jwtClaims);

        when(deviceService.findByUserIDAndDeviceID("testUserId", "testDeviceId")).
                thenReturn(new DeviceEntity(
                        UUID.randomUUID().toString(),
                        "testUserId",
                        "testDeviceId",
                        jwtClaims.getIssuedAt().getValueInMillis()
                ));
    }

    @Test
    public void getName() throws Exception {
        assertThat(loginRequestHandler.getName(), is("login"));
    }

    @Test
    public void handleWorkNormal() throws Exception {
        String sessionToken = tokenService.createSessionToken("testUserId","testDeviceId");

        when(serviceToken.createSessionToken("testUserId","testDeviceId")).
                thenReturn(sessionToken);

        LoginRequest loginData = new LoginRequest();
        loginData.setDeviceToken(deviceToken);

        loginRequest.setData(loginData);

        loginRequest.setHeader(new ActionHeader(
                new ActionHeaderBuilder().
                        withUuid("uuid").
                        withOriginUuid("origUuid").
                        withCommand("login").
                        withType("authorization").
                build()
        ));

        loginRequest.setRoutedData(new RoutedData("testDeviceId", "testUserId"));

        Response<LoginResponse> response = loginRequestHandler.handleWork(loginRequest);

        assertThat(response.getStatus().getHttpStatus(),is(HttpStatus.OK));

        assertThat(response.getHeader().getCommand(), is("login"));
        assertThat(response.getHeader().getUuid(), is("origUuid"));
        assertThat(response.getHeader().getOriginUuid(), is("uuid"));
        assertThat(response.getHeader().getType(), is("authorization"));

        assertThat(response.getData().getToken(), is(sessionToken));

    }


    @Test
    public void handleWorkTokenException() throws Exception{

        String fakeToken = "somefakeToken";

        LoginRequest loginData = new LoginRequest();
        loginData.setDeviceToken(fakeToken);

        when(serviceToken.getClaimsFromToken(fakeToken)).
                thenThrow(TokenException.class);

        loginRequest.setData(loginData);

        Response<LoginResponse> response = loginRequestHandler.handleWork(loginRequest);

        assertThat(response.getStatus().getMessage(), is(HttpStatus.BAD_REQUEST.getMsg()));
        assertThat(response.getStatus().getCode(), is(HttpStatus.BAD_REQUEST.getCode()));

    }

    @Test
    public void handleWorkForbidden() throws Exception{

        when(deviceService.findByUserIDAndDeviceID("testUserId", "testDeviceId")).
                thenReturn(new DeviceEntity(
                        UUID.randomUUID().toString(),
                        "testUserId",
                        "testDeviceId",
                        12352214L
                ));

        LoginRequest loginData = new LoginRequest();
        loginData.setDeviceToken(deviceToken);

        loginRequest.setData(loginData);

        Response<LoginResponse> response = loginRequestHandler.handleWork(loginRequest);

        assertThat(response.getStatus().getMessage(), is(HttpStatus.FORBIDDEN.getMsg()));
        assertThat(response.getStatus().getCode(), is(HttpStatus.FORBIDDEN.getCode()));
    }

}