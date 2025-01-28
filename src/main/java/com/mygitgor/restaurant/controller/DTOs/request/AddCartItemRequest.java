package com.mygitgor.restaurant.controller.DTOs.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mygitgor.restaurant.convertor.IngredientItemListStringDeserializer;
import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {
    private Long foodId;
    private int quantity;
    @JsonDeserialize(using = IngredientItemListStringDeserializer.class)
    private List<String> ingredients;
}
