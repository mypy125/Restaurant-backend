package com.mygitgor.restaurant.model.repository;

import com.mygitgor.restaurant.model.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository{
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findByRestaurantId(Long restaurantId);
    Optional<Category> findByName(String name);
}
