package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.Food;

import java.util.List;
import java.util.Optional;

public interface FoodRepository  {
    Food save(Food food);
    Optional<Food> findById(Long id);
    List<Food> findByRestaurantId(Long restaurantId);
    List<Food> searchFood(String keyword);
    void delete(Food food);
}
