package com.mygitgor.restaurant.exceptions.addressException;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}

