package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by anton on 28.02.17.
 */
public class RequestHandlerFactory<T extends RequestHandler> extends AbstractHandlerFactory<T> {

    @Autowired
    private List<T> handlers;

    @Override
    protected List<T> getHandler() {
        return handlers;
    }

    @Override
    protected String getCommand(Request<?> msg) {
        return msg.getHeader().getCommand();
    }
}
