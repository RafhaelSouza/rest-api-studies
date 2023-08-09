package com.studies.foodorders.domain.exceptions;

public class OrderNotFoundException extends NotFoundEntityException {

    public static final String THERE_IS_NO_ORDER_WITH = "There is no order with code %s";
    public OrderNotFoundException(String code) {
        super(String.format(THERE_IS_NO_ORDER_WITH, code));
    }
}
