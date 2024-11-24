package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.Food;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.service.FoodService;
import com.mygitgor.restaurant.service.RestaurantService;
import com.mygitgor.restaurant.service.UserService;
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
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam(defaultValue = "false") boolean vegetarian,
            @RequestParam(defaultValue = "false") boolean seasonal,
            @RequestParam(defaultValue = "false") boolean nonVeg,
            @RequestParam String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) {

        if (restaurantId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}
