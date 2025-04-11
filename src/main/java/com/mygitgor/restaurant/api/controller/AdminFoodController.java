package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.api.controller.DTOs.request.CreateFoodRequest;
import com.mygitgor.restaurant.api.controller.DTOs.response.MessageResponse;
import com.mygitgor.restaurant.application.service.FoodService;
import com.mygitgor.restaurant.application.service.RestaurantService;
import com.mygitgor.restaurant.application.service.UserService;
import com.mygitgor.restaurant.model.domain.Food;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Этот контроллер делегирует бизнес-логику который управляет созданием, обновлением и удалением элементов питания.
 */
@RestController
@RequestMapping("/api/admin/food")
@RequiredArgsConstructor
public class AdminFoodController {
    private final FoodService foodService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                                 @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantByUserId(user.getId());
        Food food = foodService.createFood(request);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse msg = new MessageResponse();
        msg.setMassage("food deleted successfully");

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                                   @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}
