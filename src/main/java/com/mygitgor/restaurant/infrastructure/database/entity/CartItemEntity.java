package com.mygitgor.restaurant.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cart_item")
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity extends BaseEntity{
    @JsonIgnore
    @ManyToOne
    private CartEntity cart;

    @ManyToOne
    private FoodEntity food;

    private int quantity;

    @ElementCollection
    @CollectionTable(name = "cart_item_ingredients", joinColumns = @JoinColumn(name = "cart_item_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    private Long totalPrice;
}
