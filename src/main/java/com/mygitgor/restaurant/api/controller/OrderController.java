package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.OrderRequest;
import com.mygitgor.restaurant.api.controller.DTOs.response.PaymentResponse;
import com.mygitgor.restaurant.application.service.OrderService;
import com.mygitgor.restaurant.application.service.PaymentService;
import com.mygitgor.restaurant.application.service.UserService;
import com.mygitgor.restaurant.model.domain.User;
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
        OrderEntity order = orderService.createOrder(request, user);

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
    public ResponseEntity<List<OrderEntity>> getOrderHistory(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        List<OrderEntity> order = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
