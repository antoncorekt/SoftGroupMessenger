package com.softgroup.common.protocol.utils;

import com.softgroup.common.protocol.ActionHeader;
import com.softgroup.common.protocol.Response;
import com.softgroup.common.protocol.ResponseStatus;

import java.io.Serializable;

/**
 * Created by anton on 06.04.17.
 */
public class ResponseBuilder<T extends Serializable> {
    private ResponseStatus responseStatus;

    private T data;

    private ActionHeader actionHeader;

    public ResponseBuilder withData(T data){
        this.data = data;
        return this;
    }

    public ResponseBuilder withResponseStatus(HttpStatus httpStatus){
        this.responseStatus = new ResponseStatus(httpStatus);
        return this;
    }

    public ResponseBuilder withResponseStatus(HttpStatus httpStatus, String userMsg){
        this.responseStatus = new ResponseStatus(httpStatus, userMsg);
        return this;
    }

    public ResponseBuilder withResponseStatus(ResponseStatus responseStatus){
        this.responseStatus = responseStatus;
        return this;
    }

    public ActionHeaderBuilder configureActionHeader(){
        return new ActionHeaderBuilder();
    }

    public ResponseBuilder withActionHeader(ActionHeader actionHeader){
        this.actionHeader = actionHeader;
        return this;
    }

    public Response<?> build() {
        return new Response<>(this);
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public T getData() {
        return data;
    }

    public ActionHeader getActionHeader() {
        return actionHeader;
    }
}
