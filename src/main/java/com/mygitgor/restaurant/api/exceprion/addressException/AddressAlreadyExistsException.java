package com.mygitgor.restaurant.api.exceprion.addressException;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}

