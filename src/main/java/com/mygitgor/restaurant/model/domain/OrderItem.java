package com.mygitgor.restaurant.model.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseModelId{
    private Long foodId;
    private int quantity;
    private Long totalPrice;
    private List<String> ingredients;
}
