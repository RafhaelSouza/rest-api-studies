package com.studies.foodorders.domain.exceptions;

public abstract class NotFoundEntityException extends BusinessException {

    private static final long serialVersionUID = 2034622200318008955L;

    public NotFoundEntityException(String message) {
        super(message);
    }
}
