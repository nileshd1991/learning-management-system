package com.lm.app.service.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class LmApiException extends RuntimeException {
	
	private HttpStatus httpStatus;
	
	public LmApiException(String message,HttpStatus status) {
		super(message);
		this.httpStatus=status;
	}
	
	public LmApiException(Exception e,HttpStatus status) {
		super(e);
		this.httpStatus=status;
	}
	
	
	public LmApiException(String message,Exception e,HttpStatus status) {
		super(message,e);
		this.httpStatus=status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
