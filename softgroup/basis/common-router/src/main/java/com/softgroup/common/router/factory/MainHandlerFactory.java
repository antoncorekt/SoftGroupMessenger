package com.softgroup.common.router.factory;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.CommonRouterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by anton on 03.03.17.
 */
@Component
public class MainHandlerFactory extends AbstractHandlerFactory<CommonRouterHandler> {

    @Autowired
    private List<CommonRouterHandler> commonRouterHandlerList;

    @Override
    protected List<CommonRouterHandler> getHandler() {
        return commonRouterHandlerList;
    }

    @Override
    protected String getCommand(Request<?> msg) {
        return msg.getHeader().getCommand();
    }
}
