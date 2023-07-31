package com.studies.foodorders.domain.exceptions;

public class PermissionNotFoundException extends NotFoundEntityException {

    public static final String THERE_IS_NO_PERMISSION_WITH = "There is no permission with id %d";
    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_PERMISSION_WITH, id));
    }
}
