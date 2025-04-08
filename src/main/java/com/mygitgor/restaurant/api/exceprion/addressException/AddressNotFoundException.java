package com.mygitgor.restaurant.api.exceprion.addressException;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
