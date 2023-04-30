package com.aris.springdemo.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	//catch exception for customer not found
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity(error,HttpStatus.NOT_FOUND);
	}
	//catch exception for other exceptions thrown
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
	}
	
}
