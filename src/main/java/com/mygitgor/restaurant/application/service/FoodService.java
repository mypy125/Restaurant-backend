package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.infrastructure.database.entity.CategoryEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    FoodEntity createFood(CreateFoodRequest request, CategoryEntity category, RestaurantEntity restaurant);
    void deleteFood(Long foodId)throws Exception;
    List<FoodEntity> getRestaurantFood(Long restaurantId,
                                       boolean isVegetarian,
                                       boolean isNonveg,
                                       boolean isSeasonal,
                                       String foodCategory);

    List<FoodEntity> searchFood(String keyword);
    FoodEntity findFoodById(Long id)throws Exception;
    FoodEntity updateAvailabilityStatus(Long foodId)throws Exception;

}
