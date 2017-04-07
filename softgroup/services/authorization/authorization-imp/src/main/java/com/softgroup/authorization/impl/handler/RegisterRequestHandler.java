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
import com.softgroup.common.protocol.utils.HttpStatus;
import com.softgroup.common.protocol.utils.ResponseFactory;
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

        ActionHeader header = new ActionHeader(msg.getHeader().getOriginUuid(),
                msg.getHeader().getUuid(),
                "register",
                "authorization",
                "1.1");

        RegisterResponse data = new RegisterResponse(sessionData.getUuid(),sessionData.getTimeOut(),sessionData.getAuthCode());

        smsSender.setAuthCode(sessionData.getAuthCode());
        smsSender.setNumber(sessionData.getPhoneNumber());
        smsSender.send();

        return (Response<RegisterResponse>) ResponseFactory.createResponse(header,data, HttpStatus.OK);
    }
}
