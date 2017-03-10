package com.softgroup.common.router.api;

import com.softgroup.common.router.factory.MainHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anton on 10.03.17.
 */
public abstract class AbstractMainRouter implements IMainRouter, CommonRouterHandler {

    @Autowired
    protected MainHandlerFactory mainHandlerFactory;
}
