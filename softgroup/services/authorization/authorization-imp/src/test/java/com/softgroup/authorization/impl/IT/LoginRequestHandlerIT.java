package com.softgroup.authorization.impl.IT;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.authorization.impl.config.AuthConfig;
import com.softgroup.authorization.impl.handler.LoginRequestHandler;
import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.impl.service.DeviceService;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.protocol.utils.ActionHeaderBuilder;
import com.softgroup.common.protocol.utils.Status;
import com.softgroup.common.token.impl.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anton on 12.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AuthConfig.class})
@ActiveProfiles("test")
public class LoginRequestHandlerIT {

    @Autowired
    private LoginRequestHandler loginRequestHandler;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TokenService tokenService;


    private String deviceToken;

    private String userID;
    private String deviceID;

    Request<LoginRequest> request;

    @Before
    public void init(){
        request = new Request<>();

        userID = "userTestID";
        deviceID = "device_"+ UUID.randomUUID().toString();



        request.setHeader(new ActionHeader(
                new ActionHeaderBuilder().
                        withUuid("uuid").
                        withOriginUuid("origUuid").
                        withCommand("login").
                        withType("authorization").
                        build()
        ));

        request.setRoutedData(new RoutedData(deviceID,userID));


    }

    @Test
    public void getNameTest() throws Exception{
        assertThat(loginRequestHandler.getName(), is("login"));
    }

    @Test
    public void handlerWorkTest() throws Exception{


        deviceToken = tokenService.createDeviceToken(userID,deviceID);

        deviceService.save(
                new DeviceEntity(UUID.randomUUID().toString(),
                        userID,
                        deviceID,
                        tokenService.getClaimsFromToken(deviceToken).getIssuedAt().getValueInMillis()));

        System.out.println(tokenService.getClaimsFromToken(deviceToken).getIssuedAt().getValueInMillis());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setDeviceToken(deviceToken);

        request.setData(loginRequest);

        Response<LoginResponse> response = loginRequestHandler.handleWork(request);

        assertThat(response.getStatus().getHttpStatus(),is(Status.OK));

        assertThat(response.getHeader().getCommand(), is("login"));
        assertThat(response.getHeader().getUuid(), is("origUuid"));
        assertThat(response.getHeader().getOriginUuid(), is("uuid"));
        assertThat(response.getHeader().getType(), is("authorization"));

        String resToken = response.getData().getToken();
        RoutedData resRoutedData = tokenService.getRoutedData(resToken);
        assertThat(resRoutedData.getUserID(), is(userID));
        assertThat(resRoutedData.getDeviceID(), is(deviceID));

    }
}
