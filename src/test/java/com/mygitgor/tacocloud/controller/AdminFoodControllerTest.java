package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Food;
import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.request.CreateFoodRequest;
import com.mygitgor.tacocloud.response.MessageResponse;
import com.mygitgor.tacocloud.service.FoodService;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
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
