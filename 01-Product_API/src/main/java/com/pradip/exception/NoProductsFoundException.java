package com.pradip.exception;

public class NoProductsFoundException extends RuntimeException {

	public NoProductsFoundException() {
		
	}
	
	public NoProductsFoundException(String msg) {
		super(msg);
	}
	
}
