package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.router.RouterFactoryInterface;
import com.softgroup.common.router.router.RouterHandler;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {

	@Autowired
	private RouterFactoryInterface<T> routerFactoryInterface;

	@Override
	public String getRouteKey(Request<?> msg) {

		return msg.getHeader().getCommand();
	}

	@Override
	public Response<?> handle(Request<?> msg) {

		return routerFactoryInterface.getHandler(msg).handle(msg);
	}

	public RouterFactoryInterface<T> getRouterFactoryInterface() {
		return routerFactoryInterface;
	}
}
