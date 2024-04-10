package com.mygitgor.tacocloud.request;

import com.mygitgor.tacocloud.domain.*;
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
