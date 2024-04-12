package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.IngredientCategory;
import com.mygitgor.tacocloud.domain.IngredientItem;

import java.util.List;

public interface IngredientService {
    IngredientCategory createIngredientCategory(String name, Long restaurantId)throws Exception;
    IngredientCategory findIngredientCategoryById(Long id)throws Exception;
    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;
    IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)throws Exception;
    List<IngredientItem> findRestaurantIngredients(Long restaurantId)throws Exception;
    IngredientItem updateStock(Long id)throws Exception;
}
