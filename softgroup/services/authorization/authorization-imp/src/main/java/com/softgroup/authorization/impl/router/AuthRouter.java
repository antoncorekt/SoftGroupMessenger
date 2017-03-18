package com.softgroup.authorization.impl.router;

import com.softgroup.authorization.api.router.AuthorizationRouterHandler;
import com.softgroup.authorization.impl.factory.AuthorizationRequestHandlerFactory;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 27.02.17.
 */
@Component
public class AuthRouter implements AuthorizationRouterHandler {

    @Autowired
    private AuthorizationRequestHandlerFactory authorizationRequestHandlerFactory;
    
    public String getName() {
        return "authorizations";
    }

    @Override
    public Response<?> handle(Request<?> msg) {
        Handler handler = authorizationRequestHandlerFactory.getHandler(msg);

        // todo if (handler==null) return new Request<>(400, "error") -> in other branch define constructor with param (int, str)

        return handler.handle(msg);
    }

    @Override
    public String getRouteKey(Request<?> msg) {
        return authorizationRequestHandlerFactory.getKey(msg);
    }
}
