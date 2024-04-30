package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Food;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.request.CreateFoodRequest;
import com.mygitgor.tacocloud.service.FoodService;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FoodController обрабатывает операции, связанные с ресторанными блюдами.
 */
@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @SneakyThrows
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @SneakyThrows
    @DeleteMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                 @RequestParam boolean seasonal,
                                                 @RequestParam boolean nonVeg,
                                                 @RequestParam(required = false)String food_category,
                                                 @PathVariable Long restaurantId,
                                                 @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
