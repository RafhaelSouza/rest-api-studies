package com.studies.foodorders.domain.exceptions;

public class GroupNotFoundException extends NotFoundEntityException {

    private static final long serialVersionUID = 2034622200318008955L;

    public static final String THERE_IS_NO_STATE_WITH_ID = "There is no Group with id: %s";

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_STATE_WITH_ID, id));
    }
}
