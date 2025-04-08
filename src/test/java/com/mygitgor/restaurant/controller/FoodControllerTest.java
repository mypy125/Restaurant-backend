package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.FoodController;
import com.mygitgor.restaurant.infrastructure.database.entity.FoodEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.application.service.FoodService;
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

        UserEntity user = new UserEntity();
        user.setId(1L);

        FoodEntity food1 = new FoodEntity();
        food1.setName("Sushi");
        FoodEntity food2 = new FoodEntity();
        food2.setName("Sushi Roll");

        List<FoodEntity> foods = Arrays.asList(food1, food2);

        when(userService.findUserByJwtToken(jwt)).thenReturn(user);
        when(foodService.searchFood(foodName)).thenReturn(foods);

        ResponseEntity<List<FoodEntity>> response = foodController.searchFood(foodName, jwt);
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

        UserEntity user = new UserEntity();
        user.setId(1L);

        FoodEntity food1 = new FoodEntity();
        food1.setName("Vegetarian Starter");
        FoodEntity food2 = new FoodEntity();
        food2.setName("Seasonal Salad");
        List<FoodEntity> foods = Arrays.asList(food1, food2);

        when(userService.findUserByJwtToken(jwt)).thenReturn(user);
        when(foodService.getRestaurantFood(restaurantId, vegetarian, nonVeg, seasonal, foodCategory)).thenReturn(foods);

        ResponseEntity<List<FoodEntity>> response = foodController.getRestaurantFood(vegetarian, seasonal, nonVeg, foodCategory, restaurantId, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foods, response.getBody());
    }
}
