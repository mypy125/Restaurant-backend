package com.mygitgor.restaurant.model.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCategory extends BaseModelId{
    private String name;
    private Long restaurantId;
    private List<IngredientItem> ingredients;
}