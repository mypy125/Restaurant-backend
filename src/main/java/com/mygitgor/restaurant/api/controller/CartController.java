package com.mygitgor.restaurant.api.controller;

import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.exceprion.userexception.UserNotFoundException;
import com.mygitgor.restaurant.api.controller.DTOs.request.AddCartItemRequest;
import com.mygitgor.restaurant.api.controller.DTOs.request.UpdateCartItemRequest;
import com.mygitgor.restaurant.application.service.CartService;
import com.mygitgor.restaurant.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CartController управляет операциями, связанными с корзиной пользователя.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @SneakyThrows
    @PutMapping("/cart/add")
    public ResponseEntity<CartItemEntity> addItemToCart(@RequestBody AddCartItemRequest request,
                                                        @RequestHeader("Authorization") String jwt){
        CartItemEntity cartItem = cartService.addItemToCart(request, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItemEntity> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request,
                                                                 @RequestHeader("Authorization") String jwt){
        UserEntity user = userService.findUserByJwtToken(jwt);
        if (user == null) {
            throw new UserNotFoundException("UserEntity not found");
        }
        CartItemEntity cartItem = cartService.updateCartItemQuantity(request.getId(), request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @SneakyThrows
    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<CartEntity> removeCartItem(@PathVariable Long id,
                                                     @RequestHeader("Authorization") String jwt){
        CartEntity cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @SneakyThrows
    @PutMapping("/cart/clear")
    public ResponseEntity<CartEntity> clearCart(@RequestHeader("Authorization") String jwt){

        UserEntity user = userService.findUserByJwtToken(jwt);
        CartEntity cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/cart")
    public ResponseEntity<CartEntity> findUserCart(@RequestHeader("Authorization") String jwt){

        UserEntity user = userService.findUserByJwtToken(jwt);
        CartEntity cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
