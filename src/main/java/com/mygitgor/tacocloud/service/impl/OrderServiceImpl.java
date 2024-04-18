package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.domain.*;
import com.mygitgor.tacocloud.repository.AddressRepository;
import com.mygitgor.tacocloud.repository.OrderItemRepository;
import com.mygitgor.tacocloud.repository.OrderRepository;
import com.mygitgor.tacocloud.repository.UserRepository;
import com.mygitgor.tacocloud.request.OrderRequest;
import com.mygitgor.tacocloud.service.CartService;
import com.mygitgor.tacocloud.service.OrderService;
import com.mygitgor.tacocloud.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;
    private final CartService cartService;

    @Override
    public Order createOrder(OrderRequest request, User user) throws Exception {
        Address shopAddress = request.getDeliveryAddress();
        Address saveAddress = addressRepository.save(shopAddress);

        if(!user.getAddresses().contains(saveAddress)){
            user.getAddresses().add(shopAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreateAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliveryAddress(saveAddress);
        createOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getItem()){

            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem saveOrder = orderItemRepository.save(orderItem);
            orderItems.add(saveOrder);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);

        Order saveOrder = orderRepository.save(createOrder);
        restaurant.getOrders().add(saveOrder);

        return createOrder;

    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") ||
                orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){

            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("select a valid order status");
    }

    @Override
    public void calcelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus != null){
            orders = orders.stream().filter(order ->
                    order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("order not found");
        }
        return optionalOrder.get();
    }
}
