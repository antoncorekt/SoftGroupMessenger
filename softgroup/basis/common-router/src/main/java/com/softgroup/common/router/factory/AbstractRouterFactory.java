package com.softgroup.common.router.factory;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.router.api.Handler;
import com.softgroup.common.router.router.RouterFactoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by anton on 28.02.17.
 */
public abstract class AbstractRouterFactory<T extends Handler> implements RouterFactoryInterface<T> {

    private Map<String,T> handlerMap;

    @Autowired
    private List<T> handlers;

    @PostConstruct
    public void init(){
        handlerMap = handlers.stream()
                .collect(Collectors.toMap(
                        T::getName,
                        Function.identity()));
    }

    public abstract String getKey(Request<?> msg);

    public T getHandler(Request msg){
        String key = getKey(msg);
        return handlerMap.get(key);
    }


}
