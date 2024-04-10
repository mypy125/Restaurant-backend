package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.Category;
import com.mygitgor.tacocloud.domain.Food;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.request.CreateFoodRequest;

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
