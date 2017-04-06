package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.authorization.impl.session.SessionService;
import com.softgroup.authorization.impl.session.SessionData;
import com.softgroup.authorization.impl.sms.SmsSender;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseStatus;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by anton on 29.03.17.
 */
@Component
public class RegisterRequestHandler extends AbstractRequestHandler<RegisterRequest, RegisterResponse> implements AuthorizationRequestHandler {

    @Autowired
    private SessionService serviceSession;

    @Autowired
    private SmsSender smsSender;

    @Override
    public String getName() {
        return "register";
    }

    @Override
    public Response<RegisterResponse> handleWork(Request<RegisterRequest> msg) {

        SessionData sessionData = serviceSession.addSession(msg.getData().getPhoneNumber(),
                msg.getData().getDeviceID(),
                msg.getData().getLocaleCode());

        ActionHeader header = new ActionHeader(UUID.randomUUID().toString(),
                msg.getHeader().getUuid(),
                "register",
                "authorization",
                "HTTP/1.1");

        RegisterResponse data = new RegisterResponse(sessionData.getUuid(),sessionData.getTimeOut(),sessionData.getAuthCode());
        ResponseStatus status = new ResponseStatus(200, "OK");

        smsSender.setAuthCode(sessionData.getAuthCode());
        smsSender.setNumber(sessionData.getPhoneNumber());
        smsSender.send();

        return new Response<>(header, data, status);
    }
}
