package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.LoginRequest;
import com.softgroup.authorization.api.message.LoginResponse;
import com.softgroup.authorization.api.message.RegisterResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseStatus;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by anton on 01.03.17.
 */
@Component
public class LoginRequestHandler  extends AbstractRequestHandler<LoginRequest, LoginResponse> implements AuthorizationRequestHandler  {

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public Response<LoginResponse> handleWork(Request<LoginRequest> msg) {

        LoginResponse data = new LoginResponse();
        ResponseStatus status = new ResponseStatus(200,"ok");

        ActionHeader header = new ActionHeader(UUID.randomUUID().toString(),msg.getHeader().getUuid(),"authorization",
                "login",
                "ver");


        data.setToken("token");

        Response<LoginResponse> res = new Response<>();

        res.setStatus(status);
        res.setData(data);
        res.setHeader(header);

        return res;

    }


}
