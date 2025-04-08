package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.IngredientCategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.IngredientItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
    IngredientCategoryEntity createIngredientCategory(String name, Long restaurantId)throws Exception;
    IngredientCategoryEntity findIngredientCategoryById(Long id)throws Exception;
    List<IngredientCategoryEntity> findIngredientCategoryByRestaurantId(Long id)throws Exception;
    IngredientItemEntity createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)throws Exception;
    List<IngredientItemEntity> findRestaurantIngredients(Long restaurantId)throws Exception;
    IngredientItemEntity updateStock(Long id)throws Exception;
}
