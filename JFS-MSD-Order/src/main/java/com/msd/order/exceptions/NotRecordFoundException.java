package com.msd.order.exceptions;

public class NotRecordFoundException extends RuntimeException{

	public NotRecordFoundException() {
		super("No data found based on the id");
	}
	
	public NotRecordFoundException(long id) {
		super("No data found based on the id: " + id);
	}
}
