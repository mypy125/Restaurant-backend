package com.mygitgor.tacocloud.request;

import com.mygitgor.tacocloud.domain.Category;
import com.mygitgor.tacocloud.domain.IngredientItem;
import com.mygitgor.tacocloud.domain.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category foodCategory;
    private List<String> images;

    private Restaurant restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;

    private List<IngredientItem> ingredients = new ArrayList<>();

}
