package com.msd.cart.exceptions;

public class CartDoesNotExistException extends RuntimeException {

    public CartDoesNotExistException(String message) {
        super(message);
    }
}
