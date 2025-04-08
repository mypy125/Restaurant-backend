package com.mygitgor.restaurant.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
    @ManyToOne
    private UserEntity customer;

    @JsonIgnore
    @ManyToOne
    private RestaurantEntity restaurant;

    private Long totalAmount;
    private String orderStatus;
    private Date createAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity deliveryAddress;

    @OneToMany
    private List<OrderItemEntity> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity payment;

    private int totalItem;
    private Long totalPrice;

}
