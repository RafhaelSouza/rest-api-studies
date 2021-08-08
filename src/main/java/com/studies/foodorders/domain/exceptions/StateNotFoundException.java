package com.studies.foodorders.domain.exceptions;

public class StateNotFoundException extends NotFoundEntityException {

    private static final long serialVersionUID = 2034622200318008955L;

    public static final String THERE_IS_NO_STATE_WITH_ID = "There is no State with id: %s";

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_STATE_WITH_ID, id));
    }
}
