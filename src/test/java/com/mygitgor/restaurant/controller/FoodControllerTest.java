package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.domain.Food;
import com.mygitgor.restaurant.service.FoodService;
import com.mygitgor.restaurant.service.RestaurantService;
import com.mygitgor.restaurant.service.UserService;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerTest {
    @Mock
    private FoodService foodService;
    @Mock
    private UserService userService;
    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    FoodController foodController;

    @Test
    public void testSearchFood()throws Exception{
        String foodName = "Sushi";
        String jwt = "dummy_jwt_token";

        User user = new User();
        user.setId(1L);

        Food food1 = new Food();
        food1.setName("Sushi");
        Food food2 = new Food();
        food2.setName("Sushi Roll");

        List<Food> foods = Arrays.asList(food1, food2);

        when(userService.findUserByJwtToken(jwt)).thenReturn(user);
        when(foodService.searchFood(foodName)).thenReturn(foods);

        ResponseEntity<List<Food>> response = foodController.searchFood(foodName, jwt);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foods, response.getBody());
    }

    @Test
    public void testGetRestaurantFood()throws Exception{
        Long restaurantId = 1L;
        boolean vegetarian = true;
        boolean seasonal = true;
        boolean nonVeg = false;
        String foodCategory = "Starter";
        String jwt = "dummy_jwt_token";

        User user = new User();
        user.setId(1L);

        Food food1 = new Food();
        food1.setName("Vegetarian Starter");
        Food food2 = new Food();
        food2.setName("Seasonal Salad");
        List<Food> foods = Arrays.asList(food1, food2);

        when(userService.findUserByJwtToken(jwt)).thenReturn(user);
        when(foodService.getRestaurantFood(restaurantId, vegetarian, nonVeg, seasonal, foodCategory)).thenReturn(foods);

        ResponseEntity<List<Food>> response = foodController.getRestaurantFood(vegetarian, seasonal, nonVeg, foodCategory, restaurantId, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foods, response.getBody());
    }
}
