package com.toan.ecommercedemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public InternalServerException() {
		super();
	}

	public InternalServerException(String message) {
		super(message);
	}

}
