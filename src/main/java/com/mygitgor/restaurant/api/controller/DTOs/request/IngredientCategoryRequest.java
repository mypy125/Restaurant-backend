package com.mygitgor.restaurant.api.controller.DTOs.request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restaurantId;
}
