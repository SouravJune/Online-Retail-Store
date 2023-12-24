package com.msd.cart.exceptions;

public class RecordNotFoundException extends RuntimeException{

	public RecordNotFoundException() {
		super("No data found based on the id");
	}
	
	public RecordNotFoundException(long id) {
		super("No data found based on the id: " + id);
	}
}
