package com.mahadi.InventoryManagementSystem.exceptions;

public class NameValueRequiredException extends RuntimeException{

	public NameValueRequiredException (String message) {
			/* super(message); This calls the constructor of the superclass (RuntimeException), passing the message parameter.
	The RuntimeException class has a constructor that accepts a string, which it then stores as the exception's message.
	This allows the custom exception to carry additional context or details about the error, which can be useful for debugging. */
		super(message);
	}
}
