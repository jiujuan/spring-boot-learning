package com.springboot.chapter05globalexception.exception;

public class CustomerException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomerException() {
        super();
    }

    public CustomerException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}