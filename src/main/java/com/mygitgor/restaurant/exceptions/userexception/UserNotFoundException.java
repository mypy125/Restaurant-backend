package com.mygitgor.restaurant.exceptions.userexception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
