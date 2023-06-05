package com.studies.foodorders.domain.exceptions;

public class PaymentWayNotFoundException extends NotFoundEntityException {

	private static final long serialVersionUID = 1L;

	public static final String THERE_IS_NO_PAYMENT_WAY_WITH_ID = "There is no Payment Way with id: %s";

	public PaymentWayNotFoundException(String message) {
		super(message);
	}

	public PaymentWayNotFoundException(Long id) {
		this(String.format(THERE_IS_NO_PAYMENT_WAY_WITH_ID, id));
	}
	
}
