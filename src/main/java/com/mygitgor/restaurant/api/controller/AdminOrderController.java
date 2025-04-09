package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.OrderEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.application.service.OrderService;
import com.mygitgor.restaurant.application.service.UserService;
import com.mygitgor.restaurant.model.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminOrderController определяется конечные точки для управления заказами в панели администратора.
 */
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderService orderService;
    private final UserService userService;

    @SneakyThrows
    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<OrderEntity>> getOrderHistory(@PathVariable Long id,
                                                             @RequestParam(required = false) String order_status,
                                                             @RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        List<OrderEntity> order = orderService.getRestaurantsOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<OrderEntity> updateOrderStatus(@PathVariable Long id,
                                                         @PathVariable String orderStatus,
                                                         @RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        OrderEntity order = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
