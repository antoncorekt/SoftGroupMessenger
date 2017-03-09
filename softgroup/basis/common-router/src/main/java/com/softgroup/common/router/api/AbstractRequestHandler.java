package com.softgroup.common.router.api;


import com.softgroup.common.datamapper.DataMapper;
import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.RequestData;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

public abstract class AbstractRequestHandler<T extends RequestData, R extends ResponseData> implements RequestHandler {

	@Autowired
	private DataMapper dataMapper;

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Response<R> handle(Request<?> msg) {
		Request<T> request = new Request<>();
		request.setHeader(msg.getHeader());

		final Class<T> cl = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		T t = dataMapper.convert((Map<String, Object>) msg.getData(),cl);

		request.setData(t);


		return handleWork(request);
	}

	public abstract Response<R> handleWork(Request<T> msg);

}
