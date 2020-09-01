package com.photoApp.exception;

public class UserNotFoundException extends RuntimeException {

	 private String message;
	 
	 public UserNotFoundException(String message) {
	        super("The user with username"+message+"did not find");
	        this.message = "The user with username"+message+"did not find";
	    }
}
