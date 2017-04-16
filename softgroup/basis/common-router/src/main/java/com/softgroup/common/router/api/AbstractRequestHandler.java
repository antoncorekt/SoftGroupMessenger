package com.softgroup.common.router.api;


import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.RequestData;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseData;

public class AbstractRequestHandler<T extends RequestData, R extends ResponseData> implements RequestHandler {
	@Override
	public String getName() {
		return null;
	}

	private final Class<T> cl = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	@Override
	public Response<R> handle(Request<?> msg) {
<<<<<<< HEAD
		return null;
=======
		Request<T> request = new Request<>();
		request.setHeader(msg.getHeader());


		T t = dataMapper.convert((Map<String, Object>) msg.getData(),cl);

		request.setData(t);


		return handleWork(request);
>>>>>>> master
	}

}
