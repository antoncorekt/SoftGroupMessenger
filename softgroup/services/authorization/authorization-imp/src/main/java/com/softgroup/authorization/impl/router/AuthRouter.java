package com.softgroup.authorization.impl.router;

import com.softgroup.authorization.api.router.AuthorizationRequestHandler;
import com.softgroup.authorization.api.router.AuthorizationRouterHandler;
import com.softgroup.authorization.impl.factory.AuthorizationRequestHandlerFactory;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.AbstractRouterHandler;
import com.softgroup.common.router.api.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 27.02.17.
 */
@Component
public class AuthRouter extends AbstractRouterHandler<AuthorizationRequestHandler> {

    public String getName() {
        return "authorizations";
    }

    @Override
    public String getRouteKey(Request<?> msg) {
        return getRouterFactoryInterface().getKey(msg);
    }
}
