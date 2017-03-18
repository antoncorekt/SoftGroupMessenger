package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
<<<<<<< HEAD
import com.softgroup.common.router.factory.AbstractRouterFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {

	@Autowired
	private AbstractRouterFactory<T> requestHandlerFactory;

=======

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {

>>>>>>> e5667589cad87456f9ea8f64b444eae9f025dd81
	@Override
	public String getRouteKey(Request<?> msg) {
		return null;
	}

	@Override
	public Response<?> handle(Request<?> msg) {
		return null;
	}

}
