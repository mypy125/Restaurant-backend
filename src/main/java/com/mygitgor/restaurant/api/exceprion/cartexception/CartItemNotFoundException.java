package com.mygitgor.restaurant.api.exceprion.cartexception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}

