package com.msd.product.exceptions;

public class RecordNotFoundException extends RuntimeException{

	public RecordNotFoundException() {
		super("No data found based on the id");
	}
	
	public RecordNotFoundException(long id) {
		super("No data found based on the id: " + id);
	}

	public RecordNotFoundException(String s) {
		super("No data found based on the name: " + s);
	}
}
