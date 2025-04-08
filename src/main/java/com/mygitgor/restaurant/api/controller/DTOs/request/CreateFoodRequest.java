package com.mygitgor.restaurant.api.controller.DTOs.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mygitgor.restaurant.api.convertor.ImageListDeserializer;
import com.mygitgor.restaurant.api.convertor.IngredientItemListDeserializer;
import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private CategoryEntity category;

    @JsonDeserialize(using = ImageListDeserializer.class)
    private List<String> images;

    private Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;

    @JsonDeserialize(using = IngredientItemListDeserializer.class)
    private List<IngredientItemEntity> ingredients;
}
