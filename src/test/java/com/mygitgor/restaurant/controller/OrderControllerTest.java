package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.OrderController;
import com.mygitgor.restaurant.application.service.OrderService;
import com.mygitgor.restaurant.application.service.PaymentService;
import com.mygitgor.restaurant.application.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

//    @Test
//    public void testCreateOrder()throws Exception{
//        OrderRequest request = new OrderRequest();
//        String jwt = "dummy_jwt_token";
//
//        UserEntity user = new UserEntity();
//        user.setId(1L);
//
//        OrderEntity order = new OrderEntity();
//        order.setId(1L);
//
//        PaymentResponse paymentResponse = new PaymentResponse();
//
//        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
//        when(orderService.createOrder(any(OrderRequest.class), any(UserEntity.class))).thenReturn(order);
//        when(paymentService.createStripePaymentLink(any(OrderEntity.class))).thenReturn(paymentResponse);
//
//        ResponseEntity<PaymentResponse> response = orderController.createOrder(request,user);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(paymentResponse, response.getBody());
//    }

//    @Test
//    public void testGetOrderHistory()throws Exception{
//        String jwt = "dummy_jwt_token";
//        UserEntity user = new UserEntity();
//        user.setId(1L);
//
//        OrderEntity order1 = new OrderEntity();
//        order1.setId(1L);
//
//        OrderEntity order2 = new OrderEntity();
//        order2.setId(2L);
//
//        List<OrderEntity> orders = Arrays.asList(order1, order2);
//
//        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
//        when(orderService.getUsersOrder(anyLong())).thenReturn(orders);
//
//        ResponseEntity<List<OrderEntity>> response = orderController.getOrderHistory(jwt);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(orders, response.getBody());
//    }
}
