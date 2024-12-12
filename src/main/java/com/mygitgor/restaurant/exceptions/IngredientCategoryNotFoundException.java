package com.mygitgor.restaurant.exceptions;

public class IngredientCategoryNotFoundException extends RuntimeException {
    public IngredientCategoryNotFoundException(String message) {
        super(message);
    }
}
