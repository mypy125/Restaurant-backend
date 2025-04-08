package com.mygitgor.restaurant.api.exceprion.userexception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
