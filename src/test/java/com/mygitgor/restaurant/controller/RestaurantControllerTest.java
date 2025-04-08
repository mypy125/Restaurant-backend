package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.RestaurantController;
import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.RestaurantDto;
import com.mygitgor.restaurant.application.service.RestaurantService;
import com.mygitgor.restaurant.application.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {
    @Mock
    private RestaurantService restaurantService;

    @Mock
    private UserService userService;

    @InjectMocks
    RestaurantController restaurantController;

    @Test
    public void testSearchRestaurant()throws Exception{
        String keyword = "Sushi";
        String jwt = "dummy_jwt_token";
        UserEntity user = new UserEntity();
        user.setId(1L);

        RestaurantEntity restaurant1 = new RestaurantEntity();
        restaurant1.setName("Sushi Place");
        RestaurantEntity restaurant2 = new RestaurantEntity();
        restaurant2.setName("Sushi World");

        List<RestaurantEntity>restaurants = Arrays.asList(restaurant1, restaurant2);

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.searchRestaurant(keyword)).thenReturn(restaurants);

        ResponseEntity<List<RestaurantEntity>> response = restaurantController.searchRestaurant(jwt, keyword);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurants, response.getBody());
    }

    @Test
    public void testGetAllRestaurant() throws Exception{
        String jwt = "dummy_jwt_token";
        UserEntity user = new UserEntity();
        user.setId(1L);

        RestaurantEntity restaurant1 = new RestaurantEntity();
        restaurant1.setName("Sushi Place");
        RestaurantEntity restaurant2 = new RestaurantEntity();
        restaurant2.setName("Sushi World");

        List<RestaurantEntity> restaurants = Arrays.asList(restaurant1, restaurant2);

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.getAllRestaurant()).thenReturn(restaurants);

        ResponseEntity<List<RestaurantEntity>> response = restaurantController.getAllRestaurant(jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurants, response.getBody());
    }

    @Test
    public void testFindRestaurantById()throws Exception{
        String jwt = "dummy_jwt_token";
        Long restaurantId = 1L;

        UserEntity user = new UserEntity();
        user.setId(1L);

        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(restaurantId);
        restaurant.setName("Sushi World");

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.findRestaurantById(restaurantId)).thenReturn(restaurant);

        ResponseEntity<RestaurantEntity>response = restaurantController.findRestaurantById(jwt, restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }

    @Test
    public void testAddRestaurantFavorites()throws Exception{
        String jwt = "dummy_jwt_token";
        Long restaurantId = 1L;

        UserEntity user = new UserEntity();
        user.setId(1L);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurantId);
        restaurantDto.setName("Sushi Place");

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.addToFavorites(eq(restaurantId), eq(user))).thenReturn(restaurantDto);

        ResponseEntity<RestaurantDto> response = restaurantController.addRestaurantFavorites(jwt, restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantDto, response.getBody());
    }
}
