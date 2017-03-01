package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 28.02.17.
 */
public abstract class AbstractHandlerFactory<T extends Handler> {

    private Map<String,T> handlerMap = new HashMap<>();

    public AbstractHandlerFactory(){
        for (T handler: getHandler()){
            handlerMap.put(handler.getName(),handler);
        }
    }

    public T getHandler(Request<?> msg){
        return handlerMap.get(getCommand(msg));
    }

    protected abstract List<T> getHandler();

    protected abstract String getCommand(Request<?> msg);

}
