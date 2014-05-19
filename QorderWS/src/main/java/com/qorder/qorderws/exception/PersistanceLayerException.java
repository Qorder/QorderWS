package com.qorder.qorderws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Data Access Exception while processing request!")
public class PersistanceLayerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistanceLayerException() {
		super();
	}
	
	public PersistanceLayerException(String message) {
		super(message);
	}

	public PersistanceLayerException(String message, Throwable arg1) {
		super(message, arg1);
	}
}
