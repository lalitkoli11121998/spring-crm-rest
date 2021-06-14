package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {


	//add an exception handler for customernotfoundexcetpion 
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> getexcetpion(CustomerNotFoundException ex){
	CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
	 return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND );
	}
	
	//add an exception handler for generic exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> getexcetpion(Exception ex){
	CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
	 return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST );
	}

}
