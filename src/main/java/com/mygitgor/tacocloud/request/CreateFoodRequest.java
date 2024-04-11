package com.mygitgor.tacocloud.request;

import com.mygitgor.tacocloud.domain.*;
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
