package com.mygitgor.restaurant.exceptions.addressException;

public class UnauthorizedAddressAccessException extends RuntimeException {
    public UnauthorizedAddressAccessException(String message) {
        super(message);
    }
}

