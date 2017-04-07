package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.SmsConfirmRequest;
import com.softgroup.authorization.api.message.SmsConfirmResponse;

import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.authorization.impl.session.SessionData;
import com.softgroup.authorization.impl.session.SessionService;
import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.api.entities.ProfileEntity;
import com.softgroup.common.dao.impl.service.DeviceService;
import com.softgroup.common.dao.impl.service.ProfileService;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.utils.HttpStatus;
import com.softgroup.common.protocol.utils.ResponseFactory;
import com.softgroup.common.router.api.AbstractRequestHandler;
import com.softgroup.common.token.impl.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by anton on 28.02.17.
 */
@Component
public class SmsConfirmHandler extends AbstractRequestHandler<SmsConfirmRequest,  SmsConfirmResponse> implements AuthorizationRequestHandler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SessionService sessionService;

    @Override
    public String getName() {
        return "sms_confirm";
    }


    @Override
    public Response<SmsConfirmResponse> handleWork(Request<SmsConfirmRequest> msg) {
        try{
            String uuid = msg.getData().getRegistrationID();
            String authCode = msg.getData().getAuthCode();
            SessionData sessionData = sessionService.endSession(uuid);

            if (sessionData != null && sessionData.getAuthCode().equals(authCode)){

                ProfileEntity profileEntity = profileService.findByPhoneNumber(sessionData.getPhoneNumber());

                if (profileEntity == null) {
                    profileEntity = new ProfileEntity(UUID.randomUUID().toString(),sessionData.getPhoneNumber());

                    profileEntity.setCreateDateTime(System.currentTimeMillis());
                    profileEntity.setUpdateDateTime(profileEntity.getCreateDateTime());

                    profileEntity = profileService.save( profileEntity);
                }

                String deviceToken = tokenService.createDeviceToken(sessionData.getUuid(),sessionData.getDeviceId());

                DeviceEntity deviceEntity = deviceService.findByUserIDAndDeviceID(sessionData.getUuid(),sessionData.getDeviceId());

                if (deviceEntity == null)
                    deviceEntity = new DeviceEntity(UUID.randomUUID().toString(),
                            sessionData.getUuid(),
                            sessionData.getDeviceId(),
                            null);

                deviceEntity.setConfirmaionTime(tokenService.getClaimsFromToken(deviceToken).getIssuedAt().getValueInMillis());
                deviceService.save(deviceEntity);

                ActionHeader header = new ActionHeader(msg.getHeader().getOriginUuid(),
                        msg.getHeader().getUuid(),
                        getName(),
                        "autorization",
                        "1.1");
                SmsConfirmResponse smsConfirmResponse = new SmsConfirmResponse(deviceToken);

                return (Response<SmsConfirmResponse>) ResponseFactory.createResponse(header,smsConfirmResponse,HttpStatus.OK);
            }
            else {
                return (Response<SmsConfirmResponse>) ResponseFactory.createResponse(msg,HttpStatus.FORBIDDEN);
            }

        }
        catch (Exception e){
            return (Response<SmsConfirmResponse>) ResponseFactory.createResponse(msg,HttpStatus.BAD_REQUEST);
        }
    }
}
