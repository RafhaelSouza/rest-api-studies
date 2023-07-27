package com.studies.foodorders.domain.exceptions;

public class ProductNotFoundException extends NotFoundEntityException {

    public static final String THERE_IS_NO_PRODUCT_WITH = "There is no product with id %d for restaurant id %d";
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format(THERE_IS_NO_PRODUCT_WITH, productId, restaurantId));
    }
}
