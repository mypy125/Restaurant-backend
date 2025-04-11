package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.IngredientCategory;

import java.util.List;
import java.util.Optional;

public interface IngredientCategoryRepository {
    IngredientCategory save(IngredientCategory category);
    Optional<IngredientCategory> findById(Long id);
    List<IngredientCategory> findByRestaurantId(Long restaurantId);
}
