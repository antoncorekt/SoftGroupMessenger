package com.softgroup.common.router.impl;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.*;
import com.softgroup.common.router.factory.MainHandlerFactory;
import com.softgroup.common.router.router.AbstractRouter;
import com.softgroup.common.router.router.RouterFactoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 03.03.17.
 */
@Component
@Qualifier("main")
public class MainRouter extends AbstractRouterHandler<CommonRouterHandler> {

    @Autowired
    private RouterFactoryInterface mainHandlerFactory;

    @Override
    public String getName() {
        return "mainRouter";
    }


    @Override
    protected RouterFactoryInterface<CommonRouterHandler> getFactoryClass() {
        return mainHandlerFactory;
    }
}
