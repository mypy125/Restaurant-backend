package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.AdminRestaurantController;
import com.mygitgor.restaurant.infrastructure.database.entity.RestaurantEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.CreateRestaurantRequest;
import com.mygitgor.restaurant.api.controller.DTOs.response.MessageResponse;
import com.mygitgor.restaurant.application.service.RestaurantService;
import com.mygitgor.restaurant.application.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminRestaurantControllerTest {
    @Mock
    RestaurantService restaurantService;

    @Mock
    UserService userService;

    @InjectMocks
    AdminRestaurantController adminRestaurantController;

    @Test
    public void testCreateRestaurant() throws Exception {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        UserEntity user = new UserEntity();
        RestaurantEntity restaurant = new RestaurantEntity();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.createRestaurant(eq(request), eq(user))).thenReturn(restaurant);
        ResponseEntity<RestaurantEntity> response = adminRestaurantController.createRestaurant(request, "dummy_jwt_token");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }

    @Test
    public void testUpdateRestaurant() throws Exception {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        UserEntity user = new UserEntity();
        RestaurantEntity restaurant = new RestaurantEntity();
        Long id = 1L;
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.updateRestaurant(eq(id), eq(request))).thenReturn(restaurant);
        ResponseEntity<RestaurantEntity> response = adminRestaurantController.updateRestaurant(request, "dummy_jwt_token", id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }

    @Test
    public void testDeleteRestaurant() throws Exception {
        MessageResponse expectedResponse = new MessageResponse();
        expectedResponse.setMassage("RestaurantEntity deleted success!");
        Long id = 1L;
        ResponseEntity<MessageResponse> response = adminRestaurantController.deleteRestaurant("dummy_jwt_token", id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testUpdateRestaurantStatus() throws Exception {
        RestaurantEntity restaurant = new RestaurantEntity();
        Long id = 1L;
        when(restaurantService.updateRestaurantStatus(eq(id))).thenReturn(restaurant);
        ResponseEntity<RestaurantEntity> response = adminRestaurantController.updateRestaurantStatus("dummy_jwt_token", id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }

    @Test
    public void testFindRestaurantByUserId() throws Exception {
        UserEntity user = new UserEntity();
        RestaurantEntity restaurant = new RestaurantEntity();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.findRestaurantByUserId(eq(user.getId()))).thenReturn(restaurant);
        ResponseEntity<RestaurantEntity> response = adminRestaurantController.findRestaurantByUserId("dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }
}
