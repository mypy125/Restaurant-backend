package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.Order;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.controller.DTOs.request.OrderRequest;
import com.mygitgor.restaurant.controller.DTOs.response.PaymentResponse;
import com.mygitgor.restaurant.service.OrderService;
import com.mygitgor.restaurant.service.PaymentService;
import com.mygitgor.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderController управляет операциями, связанными с заказами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final PaymentService paymentService;

    @SneakyThrows
    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest request,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @RequestParam("paymentMethod") String paymentMethod) {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(request, user);

        PaymentResponse response = switch (paymentMethod.toLowerCase()) {
            case "stripe" -> paymentService.createStripePaymentLink(order);
            case "easypay" -> paymentService.createEasyPayPaymentLink(order);
            case "idram" -> paymentService.createIdramPaymentLink(order);
            default -> throw new IllegalArgumentException("Unsupported payment method " + paymentMethod);
        };

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @SneakyThrows
    @PostMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        List<Order> order = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
