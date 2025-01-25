package com.mygitgor.restaurant.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{

    @ManyToOne
    private Food food;

    private int quantity;
    private Long totalPrice;

    private List<String> ingredients;
}
