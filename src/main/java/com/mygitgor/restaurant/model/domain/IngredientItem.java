package com.mygitgor.restaurant.model.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientItem extends BaseModelId{
    private String name;
    private Long categoryId;
    private Long restaurantId;
    private boolean inStock;
}
