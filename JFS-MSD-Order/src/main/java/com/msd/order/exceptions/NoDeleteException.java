package com.msd.order.exceptions;

public class NoDeleteException extends RuntimeException{

	public NoDeleteException() {
		super("Cannot Delete because No data found based on the id");
	}
	
	public NoDeleteException(long id) {
		super("Cannot Delete because No data found based on the id: " + id);
	}
}
