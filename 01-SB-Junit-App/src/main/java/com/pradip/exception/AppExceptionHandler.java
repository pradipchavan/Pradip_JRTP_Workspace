package com.pradip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//global exception handler
@RestControllerAdvice
public class AppExceptionHandler {
	
	//this hanldeException method get call whenever exception occurs in the entire application
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String>  hanldeException(Exception e) {
		
		//String message = e.getMessage();
		
		return new ResponseEntity<>("Exception Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
