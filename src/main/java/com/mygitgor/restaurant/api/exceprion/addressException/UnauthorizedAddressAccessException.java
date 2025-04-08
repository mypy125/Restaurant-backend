package com.mygitgor.restaurant.api.exceprion.addressException;

public class UnauthorizedAddressAccessException extends RuntimeException {
    public UnauthorizedAddressAccessException(String message) {
        super(message);
    }
}

