package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.dto.RestaurantDto;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
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
        User user = new User();
        user.setId(1L);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Sushi Place");
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Sushi World");

        List<Restaurant>restaurants = Arrays.asList(restaurant1, restaurant2);

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.searchRestaurant(keyword)).thenReturn(restaurants);

        ResponseEntity<List<Restaurant>> response = restaurantController.searchRestaurant(jwt, keyword);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurants, response.getBody());
    }

    @Test
    public void testGetAllRestaurant() throws Exception{
        String jwt = "dummy_jwt_token";
        User user = new User();
        user.setId(1L);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Sushi Place");
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Sushi World");

        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.getAllRestaurant()).thenReturn(restaurants);

        ResponseEntity<List<Restaurant>> response = restaurantController.getAllRestaurant(jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurants, response.getBody());
    }

    @Test
    public void testFindRestaurantById()throws Exception{
        String jwt = "dummy_jwt_token";
        Long restaurantId = 1L;

        User user = new User();
        user.setId(1L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName("Sushi World");

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.findRestaurantById(restaurantId)).thenReturn(restaurant);

        ResponseEntity<Restaurant>response = restaurantController.findRestaurantById(jwt, restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }

    @Test
    public void testAddRestaurantFavorites()throws Exception{
        String jwt = "dummy_jwt_token";
        Long restaurantId = 1L;

        User user = new User();
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
