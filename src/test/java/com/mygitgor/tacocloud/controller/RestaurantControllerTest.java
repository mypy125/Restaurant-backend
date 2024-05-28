package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
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
}
