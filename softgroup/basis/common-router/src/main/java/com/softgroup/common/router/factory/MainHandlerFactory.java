package com.softgroup.common.router.factory;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.CommonRouterHandler;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 03.03.17.
 */
@Component
public class MainHandlerFactory<T extends CommonRouterHandler> extends AbstractRouterFactory<T> {

    @Override
    public String getKey(Request<?> msg) {
        return msg.getHeader().getType();
    }

}
