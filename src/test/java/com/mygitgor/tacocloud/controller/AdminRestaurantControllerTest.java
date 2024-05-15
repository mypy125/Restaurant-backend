package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Restaurant;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.request.CreateRestaurantRequest;
import com.mygitgor.tacocloud.service.RestaurantService;
import com.mygitgor.tacocloud.service.UserService;
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
        User user = new User();
        Restaurant restaurant = new Restaurant();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(restaurantService.createRestaurant(eq(request), eq(user))).thenReturn(restaurant);
        ResponseEntity<Restaurant> response = adminRestaurantController.createRestaurant(request, "dummy_jwt_token");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
    }
}
