package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.router.RouterFactoryInterface;
import com.softgroup.common.router.router.RouterHandler;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {


	@Override
	public String getRouteKey(Request<?> msg) {
		return null;
	}

	@Override
	public Response<?> handle(Request<?> msg) {
		return getFactoryClass().getHandler(msg).handle(msg);
	}

	protected abstract RouterFactoryInterface<T> getFactoryClass();
}
