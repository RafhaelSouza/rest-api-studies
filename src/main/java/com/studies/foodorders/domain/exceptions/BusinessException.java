package com.studies.foodorders.domain.exceptions;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2034622200318008955L;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }
}
