package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.CreateRestaurantRequest;
import com.mygitgor.restaurant.api.controller.DTOs.response.MessageResponse;
import com.mygitgor.restaurant.application.service.RestaurantService;
import com.mygitgor.restaurant.application.service.UserService;
import com.mygitgor.restaurant.model.domain.Restaurant;
import com.mygitgor.restaurant.model.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * AdminRestaurantController определяется конечные точки для управления ресторанами в панели администратора.
 */
@RestController
@RequestMapping("/api/admin/restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    @SneakyThrows
    @PreAuthorize("hasAnyRole('OWNER', 'ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest request,
                                                             @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwtToken(jwt);

        try {
            Restaurant restaurant = restaurantService.createRestaurant(request, user);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
        response.setMassage("RestaurantEntity deleted success!");

        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @SneakyThrows
    @CrossOrigin(origins = "http://localhost:3000")
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
