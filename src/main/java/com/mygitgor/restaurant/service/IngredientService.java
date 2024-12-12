package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.IngredientCategory;
import com.mygitgor.restaurant.domain.IngredientItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
    IngredientCategory createIngredientCategory(String name, Long restaurantId)throws Exception;
    IngredientCategory findIngredientCategoryById(Long id)throws Exception;
    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;
    IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)throws Exception;
    List<IngredientItem> findRestaurantIngredients(Long restaurantId)throws Exception;
    IngredientItem updateStock(Long id)throws Exception;
}
