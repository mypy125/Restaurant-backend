package com.mygitgor.restaurant.model.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseModelId{
    private Long customerId;
    private Long restaurantId;
    private Long totalAmount;
    private String orderStatus;
    private Date createdAt;
    private Address deliveryAddress;
    private List<OrderItem> items;
    private Payment payment;
    private int totalItem;
    private Long totalPrice;
}
