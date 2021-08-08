package com.studies.foodorders.domain.exceptions;

public class KitchenNotFoundException extends NotFoundEntityException {

    private static final long serialVersionUID = 2034622200318008955L;

    public static final String THERE_IS_NO_KITCHEN_WITH_ID = "There is no Kitchen with id: %s";

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_KITCHEN_WITH_ID, id));
    }
}
