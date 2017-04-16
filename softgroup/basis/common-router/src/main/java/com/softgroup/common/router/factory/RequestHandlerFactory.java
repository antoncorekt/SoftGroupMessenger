package com.softgroup.common.router.factory;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.RequestHandler;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 28.02.17.
 */
@Component
public class RequestHandlerFactory<T extends RequestHandler> extends AbstractRouterFactory<T> {

    @Override
    public String getKey(Request<?> msg) {
        return msg.getHeader().getCommand();
    }

}
