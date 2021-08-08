package com.studies.foodorders.domain.exceptions;

public class RestaurantNotFoundException extends NotFoundEntityException {

    private static final long serialVersionUID = 2034622200318008955L;

    public static final String THERE_IS_NO_RESTAURANT_WITH_ID = "There is no Restaurant with id: %s";

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_RESTAURANT_WITH_ID, id));
    }
}
