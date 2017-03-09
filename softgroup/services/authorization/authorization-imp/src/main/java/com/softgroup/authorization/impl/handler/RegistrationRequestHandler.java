package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseStatus;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by anton on 27.02.17.
 */
@Component
public class RegistrationRequestHandler extends AbstractRequestHandler<RegisterRequest, RegisterResponse> implements AuthorizationRequestHandler {

    @Override
    public String getName() {
        return "register";
    }

    @Override
    public Response<RegisterResponse> handleWork(Request<RegisterRequest> msg) {

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setAuthCode(UUID.randomUUID().toString());
        registerResponse.setRegistrationRequestUuid(UUID.randomUUID().toString());
        registerResponse.setRegistrationTimeoutSec(10);

        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(200);
        responseStatus.setMessage("OK");

        Response<RegisterResponse> res = new Response<>();
        res.setHeader(msg.getHeader());
        res.setData(registerResponse);
        res.setStatus(responseStatus);

        return res;
    }
}
