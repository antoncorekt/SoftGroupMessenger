package com.softgroup.authorization.impl.handler;

import com.softgroup.authorization.api.message.SmsConfirmRequest;
import com.softgroup.authorization.api.message.SmsConfirmResponse;
import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.AbstractRequestHandler;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 28.02.17.
 */
@Component
public class SmsConfirmHandler extends AbstractRequestHandler<SmsConfirmRequest, SmsConfirmResponse> implements AuthorizationRequestHandler {

    @Override
    public String getName() {
        return "sms_confirm";
    }

    @Override
    public Response<SmsConfirmResponse> handleWork(Request<SmsConfirmRequest> msg) {


        return null;
    }
}
