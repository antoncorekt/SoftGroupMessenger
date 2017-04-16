package com.softgroup.common.router.api;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
<<<<<<< HEAD

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {

=======
import com.softgroup.common.router.router.RouterFactoryInterface;
import com.softgroup.common.router.router.RouterHandler;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRouterHandler<T extends Handler> implements RouterHandler {


>>>>>>> master
	@Override
	public String getRouteKey(Request<?> msg) {
		return null;
	}

	@Override
	public Response<?> handle(Request<?> msg) {
<<<<<<< HEAD
		return null;
=======
		return getFactoryClass().getHandler(msg).handle(msg);
>>>>>>> master
	}

	protected abstract RouterFactoryInterface<T> getFactoryClass();
}
