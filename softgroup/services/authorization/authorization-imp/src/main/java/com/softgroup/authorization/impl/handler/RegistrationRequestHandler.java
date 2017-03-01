package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.RegisterRequest;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.AbstractRequestHandler;
import com.softgroup.common.router.api.RequestHandler;

/**
 * Created by anton on 27.02.17.
 */
public class RegistrationRequestHandler extends AbstractRequestHandler<RegisterRequest, RegisterResponse> implements AuthorizationRequestHandler {

    @Override
    public String getName() {
        return "register";
    }


    public Response<RegisterResponse> handleWork(Request<RegisterRequest> msg) {

        ///?? testing

        return null;
    }
}
