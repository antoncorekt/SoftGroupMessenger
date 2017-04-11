package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.SmsConfirmRequest;
import com.softgroup.authorization.api.message.SmsConfirmResponse;
import com.softgroup.authorization.impl.session.SessionData;
import com.softgroup.authorization.impl.session.SessionService;
import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.service.DeviceService;
import com.softgroup.common.dao.impl.service.ProfileService;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.RoutedData;
import com.softgroup.common.token.impl.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 07.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SmsConfirmHandlerTest {

    @InjectMocks
    private SmsConfirmHandler smsConfirmHandler;

    @Spy
    private TokenService tokenService = Mockito.mock(TokenService.class);

    @Spy
    private ProfileService profileService = Mockito.mock(ProfileService.class);

    @Spy
    private DeviceService deviceService = Mockito.mock(DeviceService.class);

    @Spy
    private SessionService sessionService = Mockito.mock(SessionService.class);

    @InjectMocks
    private TokenService tokenCreater;

    private String registrID = "registrID";
    private String userID = "userID";
    private String deviceID = "deviceID";
    private String phoneNumber = "+380651870045";

    @Before
    public void init(){

    }

    @Test
    public void getName() throws Exception {
        assertThat(smsConfirmHandler.getName(), is("sms_confirm"));
    }

    @Test
    public void handleWork() throws Exception {

        Request<SmsConfirmRequest> request = new Request<>();

        SmsConfirmRequest smsConfirmRequest = new SmsConfirmRequest();
        smsConfirmRequest.setAuthCode("12345");
        smsConfirmRequest.setRegistrationID(registrID);

        request.setData(smsConfirmRequest);

        request.setRoutedData(new RoutedData("testDeviceId","testUserId"));
        request.setHeader(new ActionHeader("uuid",
                "originUuid",
                "sms_confirm",
                "authorization",
                "1.1"));

        SessionData sessionData = new SessionData(phoneNumber, deviceID, "ru");
        sessionData.setAuthCode("12345");
        sessionData.setUuid(userID);

        when(sessionService.endSession(registrID)).thenReturn(sessionData);

        when(profileService.findByPhoneNumber(phoneNumber)).thenReturn(
                new ProfileEntity(userID, phoneNumber)
        );

        String deviceToken = tokenCreater.createDeviceToken(registrID,deviceID);
        when(tokenService.createDeviceToken(userID,deviceID)).thenReturn(
                deviceToken
        );

        when(deviceService.findByUserIDAndDeviceID(userID, deviceID)).thenReturn(
                new DeviceEntity("devID", registrID,deviceID,null)
        );

        when(tokenService.getClaimsFromToken(deviceToken)).thenReturn(
                tokenCreater.getClaimsFromToken(deviceToken)
        );


        Response<SmsConfirmResponse> response = smsConfirmHandler.handleWork(request);

        System.out.println(response.getStatus().getMessage());

    }



}