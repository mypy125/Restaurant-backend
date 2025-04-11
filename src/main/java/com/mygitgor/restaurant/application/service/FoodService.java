package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.api.controller.DTOs.request.CreateFoodRequest;
import com.mygitgor.restaurant.model.domain.Food;

import java.util.List;

public interface FoodService {
    Food createFood(CreateFoodRequest request);
    void deleteFood(Long foodId)throws Exception;
    List<Food> getRestaurantFood(Long restaurantId,
                                       boolean isVegetarian,
                                       boolean isNonveg,
                                       boolean isSeasonal,
                                       String foodCategory);

    List<Food> searchFood(String keyword);
    Food findFoodById(Long id)throws Exception;
    Food updateAvailabilityStatus(Long foodId)throws Exception;

}
