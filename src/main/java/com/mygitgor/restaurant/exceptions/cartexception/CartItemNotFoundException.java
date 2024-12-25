package com.mygitgor.restaurant.exceptions.cartexception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}

