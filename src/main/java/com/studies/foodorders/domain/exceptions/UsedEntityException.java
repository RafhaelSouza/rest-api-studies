package com.studies.foodorders.domain.exceptions;

public class UsedEntityException extends BusinessException {

    private static final long serialVersionUID = 804742609602438840L;

    public UsedEntityException(String message) {
        super(message);
    }
}
