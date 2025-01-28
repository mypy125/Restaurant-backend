package com.mygitgor.restaurant.controller.DTOs.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mygitgor.restaurant.convertor.ImageListDeserializer;
import com.mygitgor.restaurant.convertor.IngredientItemListDeserializer;
import com.mygitgor.restaurant.domain.Category;
import com.mygitgor.restaurant.domain.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;

    @JsonDeserialize(using = ImageListDeserializer.class)
    private List<String> images;

    private Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;

    @JsonDeserialize(using = IngredientItemListDeserializer.class)
    private List<IngredientItem> ingredients;
}
