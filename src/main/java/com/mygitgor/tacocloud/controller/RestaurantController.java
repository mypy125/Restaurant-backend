package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.RestaurantDto;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    @SneakyThrows
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt,
                                                             @RequestParam String keyword){
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt,
                                                               @PathVariable Long id){
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    @SneakyThrows
    public ResponseEntity<RestaurantDto> addRestaurantFavorites(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long id){
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
