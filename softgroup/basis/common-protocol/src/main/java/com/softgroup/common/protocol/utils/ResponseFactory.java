package com.softgroup.common.protocol.utils;

import com.softgroup.common.protocol.*;

/**
 * Created by anton on 06.04.17.
 * @formatter:off
 */
public class ResponseFactory {

    public static <T extends ResponseData> Response<?> createResponse(Request<?> msg, T data, ResponseStatus responseStatus){

        return new ResponseBuilder<>()
                .withActionHeader(msg.getHeader())
                .withResponseStatus(responseStatus)
                .withData(data)
                .build();
    }

    public static <T extends ResponseData> Response<?> createResponse(ActionHeader header, T data, Status httpStatus){

        return new ResponseBuilder<>()
                .withActionHeader(header)
                .withResponseStatus(httpStatus)
                .withData(data)
                .build();
    }

    public static <T extends ResponseData> Response<?> createResponse(Request<?> msg, T data){

        return new ResponseBuilder<>()
                .withActionHeader(msg.getHeader())
                .withResponseStatus(Status.OK)
                .withData(msg.getData())
                .build();
    }

    public static Response<?> createResponse(Request<?> msg,  Status httpStatus){

        return new ResponseBuilder<>()
                .withActionHeader(msg.getHeader())
                .withResponseStatus(httpStatus)
                .build();
    }

    public static Response<?> createResponse(ResponseStatus responseStatus){

        return new ResponseBuilder<>()
                .withResponseStatus(responseStatus)
                .build();
    }

    public static Response<?> createResponse(Status httpStatus){

        return new ResponseBuilder<>()
                .withResponseStatus(httpStatus)
                .build();
    }
}
