package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.request.CreateRestaurantRequest;
import com.mygitgor.tacocloud.response.MessageResponse;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest request,
                                                       @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(request, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest request,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id){
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id, request);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt,
                                                            @PathVariable Long id){

        User user = userService.findUserByJwtToken(jwt);

        MessageResponse response = new MessageResponse();
        response.setMassage("Restaurant deleted success!");

        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @SneakyThrows
    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String jwt,
                                                            @PathVariable Long id){
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    @SneakyThrows
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
