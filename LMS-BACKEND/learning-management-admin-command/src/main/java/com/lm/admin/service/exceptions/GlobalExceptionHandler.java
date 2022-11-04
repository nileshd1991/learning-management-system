package com.lm.admin.service.exceptions;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lm.models.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(LmApiException.class)
	ResponseEntity<ApiError> handleException(LmApiException e){
		ApiError apiError=new ApiError();
		apiError.setMessage(e.getMessage());
		apiError.setStatusCode(e.getHttpStatus().value());
		apiError.setTimestamp(new Date());
		return new ResponseEntity<ApiError>(apiError, e.getHttpStatus());
	}
	
//	@ExceptionHandler(Exception.class)
//	ResponseEntity<String> handleExceptionForGenericException(Exception e){
//		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	}
	
}
