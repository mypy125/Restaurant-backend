package com.mygitgor.restaurant.exceptions.addressException;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
