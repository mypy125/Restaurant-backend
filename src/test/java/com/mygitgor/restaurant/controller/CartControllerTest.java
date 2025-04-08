package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.api.controller.CartController;
import com.mygitgor.restaurant.infrastructure.database.entity.CartEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import com.mygitgor.restaurant.infrastructure.database.entity.UserEntity;
import com.mygitgor.restaurant.api.controller.DTOs.request.AddCartItemRequest;
import com.mygitgor.restaurant.application.service.CartService;
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
public class CartControllerTest {
    @Mock
    private CartService cartService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CartController cartController;

    @Test
    public void testAddItemToCart() throws Exception{
        AddCartItemRequest request = new AddCartItemRequest();
        CartItemEntity cartItem = new CartItemEntity();
        when(cartService.addItemToCart(eq(request), anyString())).thenReturn(cartItem);
        ResponseEntity<CartItemEntity> response = cartController.addItemToCart(request, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
    }

//    @Test
//    public void testUpdateCartItemQuantity() throws Exception{
//        UpdateCartItemRequest request = new UpdateCartItemRequest();
//        request.setId(1L);
//        request.setQuantity(2);
//        CartItemEntity cartItem = new CartItemEntity();
//        when(cartService.updateCartItemQuantity(eq(1L), eq(2))).thenReturn(cartItem);
//        ResponseEntity<CartItemEntity> response = cartController.updateCartItemQuantity(request, "dummy_jwt_token");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(cartItem, response.getBody());
//    }

    @Test
    public void testRemoveCartItem() throws Exception{
        CartEntity cart = new CartEntity();
        when(cartService.removeItemFromCart(eq(1L), anyString())).thenReturn(cart);
        ResponseEntity<CartEntity> response = cartController.removeCartItem(1L, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testClearCart() throws Exception{
        UserEntity user = new UserEntity();
        user.setId(1L);
        CartEntity cart = new CartEntity();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(cartService.clearCart(eq(1L))).thenReturn(cart);
        ResponseEntity<CartEntity> response = cartController.clearCart("dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testFindUserCart() throws Exception{
        UserEntity user = new UserEntity();
        user.setId(1L);
        CartEntity cart = new CartEntity();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(cartService.findCartByUserId(eq(1L))).thenReturn(cart);
        ResponseEntity<CartEntity> response = cartController.findUserCart("dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }
}
