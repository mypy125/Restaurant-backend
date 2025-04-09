package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.RestaurantDto;
import com.mygitgor.restaurant.application.service.RestaurantService;
import com.mygitgor.restaurant.application.service.UserService;
import com.mygitgor.restaurant.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestaurantController управляет операциями, связанными с ресторанами.
 */
@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantEntity>> searchRestaurant(@RequestHeader("Authorization") String jwt,
                                                                   @RequestParam String keyword)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<RestaurantEntity> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurant(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<RestaurantEntity> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantEntity> findRestaurantById(@RequestHeader("Authorization") String jwt,
                                                               @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        RestaurantEntity restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addRestaurantFavorites(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long id)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}/remove-favorites")
    public ResponseEntity<Void> removeRestaurantFromFavorites(@RequestHeader("Authorization") String jwt,
                                                                @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        restaurantService.removeFromFavorites(id, user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
