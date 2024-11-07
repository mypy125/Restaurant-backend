package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.service.OrderService;
import com.mygitgor.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminOrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminOrderController adminOrderController;

    @Test
    public void testGetOrderHistory() throws Exception {
        List<Order> orders = new ArrayList<>();
        when(userService.findUserByJwtToken(anyString())).thenReturn(new User());
        when(orderService.getRestaurantsOrder(anyLong(), anyString())).thenReturn(orders);
        ResponseEntity<List<Order>> response = adminOrderController.getOrderHistory(1L, "status", "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    public void testUpdateOrderStatus() throws Exception {
        Order order = new Order();
        when(userService.findUserByJwtToken(anyString())).thenReturn(new User());
        when(orderService.updateOrder(anyLong(), anyString())).thenReturn(order);
        ResponseEntity<Order> response = adminOrderController.updateOrderStatus(1L, "status", "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }
}