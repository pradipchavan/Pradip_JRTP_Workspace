package com.pradip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pradip.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

	//any exception
	@ExceptionHandler(exception = NullPointerException.class)
	public ResponseEntity<ApiResponse<String>> handleException(NullPointerException npe) {
		
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		apiResponse.setStatus(500);
		apiResponse.setMessage(npe.getMessage());
		apiResponse.setData(null);
		
		log.error(npe.getMessage());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//any exception
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
		
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		apiResponse.setStatus(500);
		apiResponse.setMessage(e.getMessage());
		apiResponse.setData(null);
		
		log.error(e.getMessage());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
