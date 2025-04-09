package com.mygitgor.restaurant.application.service;

import com.mygitgor.restaurant.api.controller.DTOs.request.CreateFoodRequest;
import com.mygitgor.restaurant.model.domain.Category;
import com.mygitgor.restaurant.model.domain.Food;
import com.mygitgor.restaurant.model.domain.Restaurant;

import java.util.List;

public interface FoodService {
    Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);
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
