package com.mygitgor.restaurant.model.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseModelId{
    private Long cartId;
    private Long foodId;
    private int quantity;
    private List<String> ingredients;
    private Long totalPrice;
}

