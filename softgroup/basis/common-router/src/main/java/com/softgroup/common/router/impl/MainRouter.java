package com.softgroup.common.router.impl;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.api.*;
import com.softgroup.common.router.factory.MainHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anton on 03.03.17.
 */
@Component
public class MainRouter extends AbstractRouterHandler<CommonRouterHandler> {

    @Override
    public String getName() {
        return "mainRouter";
    }

}
