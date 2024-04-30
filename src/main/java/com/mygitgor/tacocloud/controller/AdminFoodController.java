package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Food;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.request.CreateFoodRequest;
import com.mygitgor.tacocloud.response.MessageResponse;
import com.mygitgor.tacocloud.service.FoodService;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
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
    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        Food food = foodService.createFood(request, request.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @SneakyThrows
    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        foodService.findFoodById(id);

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
