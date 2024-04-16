package com.mygitgor.tacocloud.service;

import com.mygitgor.tacocloud.domain.Cart;
import com.mygitgor.tacocloud.domain.CartItem;
import com.mygitgor.tacocloud.request.AddCartItemRequest;

public interface CartService {
    CartItem addItemToCart(AddCartItemRequest request, String jwt)throws Exception;
    CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws Exception;
    Cart removeItemFromCart(Long id, String jwt)throws Exception;
    Long calculateCartTotals(Cart cart)throws Exception;
    Cart findCartById(Long id)throws Exception;
    Cart findCartByUserId(String jwt)throws Exception;
    Cart clearCart(String jwt)throws Exception;
}
