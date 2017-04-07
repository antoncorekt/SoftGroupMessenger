package com.softgroup.common.protocol;

import com.softgroup.common.protocol.utils.HttpStatus;

import java.io.Serializable;

public class ResponseStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;

    public ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseStatus(HttpStatus httpStatus){
        this.code = httpStatus.getCode();
        this.message = httpStatus.getMsg();
    }

    public ResponseStatus(HttpStatus httpStatus, String userMsg){
        this.code = httpStatus.getCode();
        this.message = httpStatus.getMsg() + " " + userMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.valueOf(getMessage());
    }

}
