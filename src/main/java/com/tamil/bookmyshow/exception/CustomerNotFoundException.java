package com.tamil.bookmyshow.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
