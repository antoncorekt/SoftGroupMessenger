package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.dao.api.entities.DeviceEntity;
import com.softgroup.common.dao.impl.service.DeviceService;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.utils.Status;
import com.softgroup.common.protocol.utils.ResponseFactory;
import com.softgroup.common.router.api.AbstractRequestHandler;
import com.softgroup.common.token.api.TokenException;
import com.softgroup.common.token.impl.service.TokenService;
import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

            DeviceEntity device  = deviceService.findByUserIDAndDeviceID(userId,deviceId);

            if (device!=null &&
                    jwtClaims.getIssuedAt().getValueInMillis() == device.getConfirmaionTime()) {


                ActionHeader header = new ActionHeader(msg.getHeader().getOriginUuid(),
                        msg.getHeader().getUuid(),
                        "login",
                        "authorization",
                        "1.1");

                LoginResponse data = new LoginResponse(serviceToken.createSessionToken(userId, deviceId));

                return (Response<LoginResponse>)ResponseFactory.createResponse(header,data, Status.OK);
            }
            else{
                return (Response<LoginResponse>) ResponseFactory.createResponse(Status.FORBIDDEN);
            }
        }
        catch (TokenException e){
          return (Response<LoginResponse>) ResponseFactory.createResponse(Status.BAD_REQUEST);
        }
        catch (Exception e){
            return (Response<LoginResponse>) ResponseFactory.createResponse(Status.INTERNAL_SERVER_ERROR);
        }



    }


}
