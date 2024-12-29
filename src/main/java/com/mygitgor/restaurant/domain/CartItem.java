package com.mygitgor.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mygitgor.restaurant.convertor.IngredientItemListDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Food food;

    private int quantity;

    @ElementCollection
    @CollectionTable(name = "cart_item_ingredients", joinColumns = @JoinColumn(name = "cart_item_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    private Long totalPrice;
}
