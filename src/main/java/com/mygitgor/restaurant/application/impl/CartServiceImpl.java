package com.mygitgor.restaurant.application.impl;

import com.mygitgor.restaurant.api.exceprion.cartexception.CartItemNotFoundException;
import com.mygitgor.restaurant.model.domain.Cart;
import com.mygitgor.restaurant.model.domain.CartItem;
import com.mygitgor.restaurant.model.domain.Food;
import com.mygitgor.restaurant.model.domain.User;
import com.mygitgor.restaurant.model.repository.CartItemRepository;
import com.mygitgor.restaurant.model.repository.CartRepository;
import com.mygitgor.restaurant.api.controller.DTOs.request.AddCartItemRequest;
import com.mygitgor.restaurant.application.service.CartService;
import com.mygitgor.restaurant.application.service.FoodService;
import com.mygitgor.restaurant.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CartService: Этот сервиз связаны с  корзиной покупок.
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodService foodService;
    private final UserService userService;

    /**
     * метод предназначен для добавления товара в корзину пользователя.
     * @param request пренимает foodId quantity ingredients
     * @param jwt  пренимает токен пользователя
     * @return возврошяет корзину товаров пользователя
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFoodId().equals(food.getId())) {
                int quantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), quantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFoodId(food.getId());
        newCartItem.setCartId(cart.getId());
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity() * food.getPrice());

        return cartItemRepository.save(newCartItem);
    }

    /**
     * метод предназначен для обновления количества товара в корзине пользователя
     * @param cartItemId принемает идентификатор карзины
     * @param quantity принемает количество товаров
     * @return возврошает корзину товаров
     * @throws Exception бросает исключения Exception
     */
    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws CartItemNotFoundException{
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found"));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getTotalPrice() / cartItem.getQuantity() * quantity);
        return cartItemRepository.save(cartItem);
    }

    /**
     * метод предназначен для удаления элемента из корзины пользователя.
     * @param itemId идентификатор карзины
     * @param jwt токен пользователя
     * @return  возврашает карзину
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Cart removeItemFromCart(Long itemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        boolean removed = cart.getItems().removeIf(item -> item.getId().equals(itemId));
        if (!removed) throw new Exception("Cart item not found");

        return cartRepository.save(cart);
    }

    /**
     * метод предназначен для расчета общей стоимости товаров в корзине.
     * @param cart принимает карзину
     * @return возврошает стоимость товаров
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        return cart.getItems().stream()
                .mapToLong(CartItem::getTotalPrice)
                .sum();
    }

    /**
     * метод предназначен для поиска корзины по её идентификатору.
     * @param id идентификатор карзины
     * @return возврошает карзину
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Cart findCartById(Long id) throws Exception {
        return cartRepository.findById(id)
                .orElseThrow(() -> new Exception("Cart not found with id " + id));
    }

    /**
     * метод предназначен для поиска корзины по идентификатору пользователя.
     * @param userId идентификатор пользователя
     * @return возврошает карзину пользователя
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    /**
     * метод clearCart предназначен для очистки корзины пользователя.
     * @param userId идентификатор пользователя
     * @return возбрашает карзину
     * @throws Exception бросает исключения Exception
     */
    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
