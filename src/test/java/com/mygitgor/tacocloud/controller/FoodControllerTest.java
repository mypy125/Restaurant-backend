package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.domain.Food;
import com.mygitgor.tacocloud.service.FoodService;
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
}
