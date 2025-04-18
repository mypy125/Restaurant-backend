package com.mygitgor.restaurant.controller;

import com.mygitgor.restaurant.domain.Cart;
import com.mygitgor.restaurant.domain.CartItem;
import com.mygitgor.restaurant.domain.User;
import com.mygitgor.restaurant.controller.DTOs.request.AddCartItemRequest;
import com.mygitgor.restaurant.controller.DTOs.request.UpdateCartItemRequest;
import com.mygitgor.restaurant.service.CartService;
import com.mygitgor.restaurant.service.UserService;
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
        CartItem cartItem = new CartItem();
        when(cartService.addItemToCart(eq(request), anyString())).thenReturn(cartItem);
        ResponseEntity<CartItem> response = cartController.addItemToCart(request, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
    }

//    @Test
//    public void testUpdateCartItemQuantity() throws Exception{
//        UpdateCartItemRequest request = new UpdateCartItemRequest();
//        request.setId(1L);
//        request.setQuantity(2);
//        CartItem cartItem = new CartItem();
//        when(cartService.updateCartItemQuantity(eq(1L), eq(2))).thenReturn(cartItem);
//        ResponseEntity<CartItem> response = cartController.updateCartItemQuantity(request, "dummy_jwt_token");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(cartItem, response.getBody());
//    }

    @Test
    public void testRemoveCartItem() throws Exception{
        Cart cart = new Cart();
        when(cartService.removeItemFromCart(eq(1L), anyString())).thenReturn(cart);
        ResponseEntity<Cart> response = cartController.removeCartItem(1L, "dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testClearCart() throws Exception{
        User user = new User();
        user.setId(1L);
        Cart cart = new Cart();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(cartService.clearCart(eq(1L))).thenReturn(cart);
        ResponseEntity<Cart> response = cartController.clearCart("dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testFindUserCart() throws Exception{
        User user = new User();
        user.setId(1L);
        Cart cart = new Cart();
        when(userService.findUserByJwtToken(anyString())).thenReturn(user);
        when(cartService.findCartByUserId(eq(1L))).thenReturn(cart);
        ResponseEntity<Cart> response = cartController.findUserCart("dummy_jwt_token");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }
}
