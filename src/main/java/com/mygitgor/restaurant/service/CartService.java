package com.mygitgor.restaurant.service;

import com.mygitgor.restaurant.domain.Cart;
import com.mygitgor.restaurant.domain.CartItem;
import com.mygitgor.restaurant.exceptions.cartexception.CartItemNotFoundException;
import com.mygitgor.restaurant.controller.DTOs.request.AddCartItemRequest;

public interface CartService {
    CartItem addItemToCart(AddCartItemRequest request, String jwt)throws Exception;
    CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws CartItemNotFoundException;
    Cart removeItemFromCart(Long id, String jwt)throws Exception;
    Long calculateCartTotals(Cart cart)throws Exception;
    Cart findCartById(Long id)throws Exception;
    Cart findCartByUserId(Long userId)throws Exception;
    Cart clearCart(Long userId)throws Exception;
}
