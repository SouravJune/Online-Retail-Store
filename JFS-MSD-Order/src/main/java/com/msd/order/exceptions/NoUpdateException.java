package com.msd.order.exceptions;

public class NoUpdateException extends RuntimeException{

	public NoUpdateException() {
		super("No data found based on the id, So unable to update");
	}
	
	public NoUpdateException(long id) {
		super("No data found based on the id: " + id + ", So unable to update");
	}
}
