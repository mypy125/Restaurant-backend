package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.request.OrderRequest;
import com.mygitgor.restaurant.response.PaymentResponse;
import com.mygitgor.restaurant.service.OrderService;
import com.mygitgor.restaurant.service.PaymentService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private UserService userService;
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    OrderController orderController;

    @Test
    public void testCreateOrder()throws Exception{
        OrderRequest request = new OrderRequest();
        String jwt = "dummy_jwt_token";

        User user = new User();
        user.setId(1L);

        Order order = new Order();
        order.setId(1L);

        PaymentResponse paymentResponse = new PaymentResponse();

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(orderService.createOrder(any(OrderRequest.class), any(User.class))).thenReturn(order);
        when(paymentService.createPaymentLink(any(Order.class))).thenReturn(paymentResponse);

        ResponseEntity<PaymentResponse> response = orderController.createOrder(request, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentResponse, response.getBody());
    }

    @Test
    public void testGetOrderHistory()throws Exception{
        String jwt = "dummy_jwt_token";
        User user = new User();
        user.setId(1L);

        Order order1 = new Order();
        order1.setId(1L);

        Order order2 = new Order();
        order2.setId(2L);

        List<Order> orders = Arrays.asList(order1, order2);

        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(orderService.getUsersOrder(anyLong())).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderController.getOrderHistory(jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }
}
