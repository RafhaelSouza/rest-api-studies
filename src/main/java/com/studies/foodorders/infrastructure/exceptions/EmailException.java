package com.studies.foodorders.infrastructure.exceptions;

public class EmailException extends RuntimeException {

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(String message) {
		super(message);
	}

}
