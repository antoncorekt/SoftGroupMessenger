package com.softgroup.common.protocol.utils;

/**
 * Created by anton on 06.04.17.
 */
public enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad request"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not found"),
    NOT_ACCEPTABLE(406, "Not acceptable"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable entities"),
    TO_MANY_REQUESTS(429, "To many requests"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    NOT_IMPLEMENTED(501, "Not implemented");

    private final Integer code;

    private final String msg;

    HttpStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg ;

    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
