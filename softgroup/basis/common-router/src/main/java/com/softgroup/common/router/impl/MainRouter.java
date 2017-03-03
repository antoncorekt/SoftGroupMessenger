package com.softgroup.common.router.impl;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.CommonRouterHandler;
import com.softgroup.common.router.api.IMainRouter;
import com.softgroup.common.router.factory.MainHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 03.03.17.
 */
@Component
public class MainRouter implements IMainRouter, CommonRouterHandler {

    @Autowired
    private MainHandlerFactory mainHandlerFactory;

    @Override
    public String getRouteKey(Request<?> msg) {
        return msg.getHeader().getCommand();
    }

    @Override
    public String getName() {
        return "mainRouter";
    }

    @Override
    public Response<?> handle(Request<?> msg) {
        return mainHandlerFactory.getHandler(msg).handle(msg);
    }
}
