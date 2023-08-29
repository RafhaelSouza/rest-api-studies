package com.studies.foodorders.domain.exceptions;

public class ProductPhotoNotFoundException extends NotFoundEntityException {

	public ProductPhotoNotFoundException(String message) {
		super(message);
	}

	public ProductPhotoNotFoundException(Long restaurantId, Long productId) {
		this(String.format("There is no product photo with id %d for the restaurant with id %d",
				productId, restaurantId));
	}

}