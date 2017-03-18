package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.router.factory.AbstractRouterFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {

	@Autowired
	private AbstractRouterFactory<T> requestHandlerFactory;

	@Override
	public String getRouteKey(Request<?> msg) {

		return msg.getHeader().getCommand();
	}

	@Override
	public Response<?> handle(Request<?> msg) {

		return requestHandlerFactory.getHandler(msg).handle(msg);
	}

}
