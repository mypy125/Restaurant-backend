package com.mygitgor.tacocloud.controller;

import com.mygitgor.tacocloud.domain.CartItem;
import com.mygitgor.tacocloud.request.AddCartItemRequest;
import com.mygitgor.tacocloud.request.UpdateCartItemRequest;
import com.mygitgor.tacocloud.service.CartService;
import com.mygitgor.tacocloud.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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
        CartItem cartItem = new CartItem();
        when(cartService.addItemToCart(eq(request), anyString())).thenReturn(cartItem);
        ResponseEntity<CartItem> response = cartController.addItemToCart(request, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
    }

    @Test
    public void testUpdateCartItemQuantity() throws Exception{
        UpdateCartItemRequest request = new UpdateCartItemRequest();
        request.setId(1L);
        request.setQuantity(2);
        CartItem cartItem = new CartItem();
        when(cartService.updateCartItemQuantity(eq(1L), eq(2))).thenReturn(cartItem);
        ResponseEntity<CartItem> response = cartController.updateCartItemQuantity(request, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
    }

}
