package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.IngredientItem;

import java.util.List;
import java.util.Optional;

public interface IngredientItemRepository{
    IngredientItem save(IngredientItem item);
    Optional<IngredientItem> findById(Long id);
    List<IngredientItem> findByRestaurantId(Long restaurantId);
    List<IngredientItem> findAllById(List<Long> ids);
}
