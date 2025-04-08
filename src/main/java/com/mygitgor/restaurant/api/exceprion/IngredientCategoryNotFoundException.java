package com.mygitgor.restaurant.api.exceprion;

public class IngredientCategoryNotFoundException extends RuntimeException {
    public IngredientCategoryNotFoundException(String message) {
        super(message);
    }
}
