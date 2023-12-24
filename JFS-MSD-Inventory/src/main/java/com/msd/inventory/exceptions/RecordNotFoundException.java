package com.msd.inventory.exceptions;

public class RecordNotFoundException extends RuntimeException{

	public RecordNotFoundException() {
		super("No data found in the database");
	}
	
	public RecordNotFoundException(long id) {
		super("No data found based on the id: " + id);
	}

	public RecordNotFoundException(String s) {
		super("No data found based on the name: " + s);
	}
}
