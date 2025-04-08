package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.application.service.FoodService;
import com.mygitgor.restaurant.application.service.RestaurantService;
import com.mygitgor.restaurant.application.service.UserService;
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
    public ResponseEntity<List<FoodEntity>> searchFood(@RequestParam String name,
                                                       @RequestHeader("Authorization") String jwt){
        UserEntity user = userService.findUserByJwtToken(jwt);

        List<FoodEntity> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodEntity>> getRestaurantFood(
            @RequestParam(defaultValue = "false") boolean vegetarian,
            @RequestParam(defaultValue = "false") boolean seasonal,
            @RequestParam(defaultValue = "false") boolean nonVeg,
            @RequestParam String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) {

        if (restaurantId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserEntity user = userService.findUserByJwtToken(jwt);
        List<FoodEntity> foods = foodService.getRestaurantFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}
