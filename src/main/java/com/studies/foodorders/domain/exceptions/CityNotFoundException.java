package com.studies.foodorders.domain.exceptions;

public class CityNotFoundException extends NotFoundEntityException {

    private static final long serialVersionUID = 2034622200318008955L;

    public static final String THERE_IS_NO_CITY_WITH_ID = "There is no City with id: %s";

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_CITY_WITH_ID, id));
    }
}
