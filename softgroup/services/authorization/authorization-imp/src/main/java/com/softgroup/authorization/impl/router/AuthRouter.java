package com.softgroup.authorization.impl.router;

import com.softgroup.authorization.impl.handler.RegistrationRequestHandler;
import com.softgroup.common.router.api.AbstractRouterHandler;
import com.softgroup.common.router.api.CommonRouterHandler;
import com.softgroup.common.router.api.Handler;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 27.02.17.
 */
@Component
public class AuthRouter<T extends RegistrationRequestHandler> extends AbstractRouterHandler<T> implements CommonRouterHandler {

    
    public String getName() {
        return "authorizations";
    }
}
