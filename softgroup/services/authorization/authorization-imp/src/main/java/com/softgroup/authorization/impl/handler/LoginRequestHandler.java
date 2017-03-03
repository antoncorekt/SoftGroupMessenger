package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 01.03.17.
 */
@Component
public class LoginRequestHandler  extends AbstractRequestHandler<LoginRequest, LoginResponse> implements AuthorizationRequestHandler {

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public Response<LoginResponse> handleWork(Request<LoginRequest> msg) {
        return null;
    }
}
