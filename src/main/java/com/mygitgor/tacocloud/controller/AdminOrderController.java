package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.Order;
import com.mygitgor.tacocloud.domain.User;
import com.mygitgor.tacocloud.service.OrderService;
import com.mygitgor.tacocloud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderService orderService;
    private final UserService userService;

    @SneakyThrows
    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id,
                                                       @RequestParam(required = false) String order_status,
                                                       @RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        List<Order> order = orderService.getRestaurantsOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                       @PathVariable String orderStatus,
                                                       @RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwtToken(jwt);

        Order order = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
