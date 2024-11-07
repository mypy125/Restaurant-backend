package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.Food;
import com.mygitgor.restaurant.domain.Restaurant;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.request.CreateFoodRequest;
import com.mygitgor.restaurant.response.MessageResponse;
import com.mygitgor.restaurant.service.FoodService;
import com.mygitgor.restaurant.service.RestaurantService;
import com.mygitgor.restaurant.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminFoodControllerTest {

    @Mock
    private FoodService foodService;

    @Mock
    private UserService userService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private AdminFoodController adminFoodController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateFood() throws Exception {
        CreateFoodRequest request = new CreateFoodRequest();
        request.setRestaurantId(1L);
        Food food = new Food();
        when(userService.findUserByJwtToken(anyString())).thenReturn(new User());
        when(restaurantService.findRestaurantById(anyLong())).thenReturn(new Restaurant());
        when(foodService.createFood(any(), any(), any())).thenReturn(food);
        ResponseEntity<Food> response = adminFoodController.createFood(request, "dummy_jwt_token");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(food, response.getBody());
    }

    @Test
    public void testDeleteFood() throws Exception {
        when(userService.findUserByJwtToken(anyString())).thenReturn(new User());
        when(foodService.findFoodById(anyLong())).thenReturn(new Food());
        ResponseEntity<MessageResponse> response = adminFoodController.deleteFood(1L, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("food deleted successfully", response.getBody().getMessage());
    }

    @Test
    public void testUpdateFoodAvailabilityStatus() throws Exception {
        Food food = new Food();
        when(userService.findUserByJwtToken(anyString())).thenReturn(new User());
        when(foodService.updateAvailabilityStatus(anyLong())).thenReturn(food);
        ResponseEntity<Food> response = adminFoodController.updateFoodAvailabilityStatus(1L, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(food, response.getBody());
    }
}
