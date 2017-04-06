package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.dao.impl.service.DeviceService;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseStatus;
import com.softgroup.common.router.api.AbstractRequestHandler;
import com.softgroup.common.token.impl.service.TokenService;
import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by anton on 01.03.17.
 */
@Component
public class LoginRequestHandler  extends AbstractRequestHandler<LoginRequest, LoginResponse> implements AuthorizationRequestHandler  {

    @Autowired
    private TokenService serviceToken;

    @Autowired
    private DeviceService deviceService;

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public Response<LoginResponse> handleWork(Request<LoginRequest> msg) {

        try {
            String deviceToken = msg.getData().getDeviceToken();
            JwtClaims jwtClaims = serviceToken.getClaimsFromToken(deviceToken);
            String userId = jwtClaims.getStringClaimValue("userID");
            String deviceId = jwtClaims.getStringClaimValue("deviceID");

            if (deviceService.findByUserIDAndDeviceID(userId,deviceId)!=null &&
                    jwtClaims.getIssuedAt().getValueInMillis() == deviceService.findByUserIDAndDeviceID(userId,deviceId).getConfirmaionTime()) {


                ActionHeader header = new ActionHeader(UUID.randomUUID().toString(),
                        msg.getHeader().getUuid(),
                        "login",
                        "authorization",
                        "HTTP/1.1");

                LoginResponse data = new LoginResponse(serviceToken.createSessionToken(userId, deviceId));

                return new Response<>(header, data, new ResponseStatus(200, "OK"));
            }
            else{
                return new Response<>(null,null, new ResponseStatus(402, "Invalid token or user not found"));
            }
        }
        catch (Exception e){
            return new Response<>(null, null, new ResponseStatus(403, "Bad request"));
        }


    }


}
