package com.studies.foodorders.domain.exceptions;

public class OrderNotFoundException extends NotFoundEntityException {

    public static final String THERE_IS_NO_ORDER_WITH = "There is no order with id %d";
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long id) {
        this(String.format(THERE_IS_NO_ORDER_WITH, id));
    }
}
