package com.mygitgor.restaurant.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity extends BaseEntity{

    @ManyToOne
    private FoodEntity food;

    private int quantity;
    private Long totalPrice;

    private List<String> ingredients;
}
