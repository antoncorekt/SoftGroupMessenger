package com.softgroup.common.protocol.utils;

import com.softgroup.common.protocol.Request;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseData;
import com.softgroup.common.protocol.ResponseStatus;

/**
 * Created by anton on 06.04.17.
 * @formatter:off
 */
public class ResponseFactory {

    public static <T extends ResponseData> Response<?> createResponse(Request<?> msg, T data, ResponseStatus responseStatus){

        return new ResponseBuilder<>()
                .withActionHeader(msg.getHeader())
                .withResponseStatus(responseStatus)
                .withData(msg.getData())
                .build();
    }

    public static <T extends ResponseData> Response<?> createResponse(Request<?> msg, T data){

        return new ResponseBuilder<>()
                .withActionHeader(msg.getHeader())
                .withResponseStatus(HttpStatus.OK)
                .withData(msg.getData())
                .build();
    }

    public static Response<?> createResponse(Request<?> msg,  ResponseStatus responseStatus){

        return new ResponseBuilder<>()
                .withActionHeader(msg.getHeader())
                .withResponseStatus(responseStatus)
                .build();
    }

     public static Response<?> createResponse(ResponseStatus responseStatus){

        return new ResponseBuilder<>()
                .withResponseStatus(responseStatus)
                .build();
        }

    public static Response<?> createResponse(HttpStatus httpStatus){

        return new ResponseBuilder<>()
                .withResponseStatus(httpStatus)
                .build();
    }
}
