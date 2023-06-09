package com.studies.foodorders.domain.exceptions;

public class UserNotFoundException extends NotFoundEntityException {

    private static final long serialVersionUID = 2034622200318008955L;

    public static final String THERE_IS_NO_USER_WITH_ID = "There is no User with id: %s";

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_USER_WITH_ID, id));
    }
}
