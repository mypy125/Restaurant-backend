package com.mygitgor.restaurant.request;

import com.mygitgor.restaurant.domain.Category;
import com.mygitgor.restaurant.domain.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Category category;

    private List<String> images;
    private Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;

    private List<IngredientItem> ingredients;

}
