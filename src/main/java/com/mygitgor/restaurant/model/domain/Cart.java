package com.mygitgor.restaurant.model.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseModelId{
    private Long customerId;
    private Long total;
    private List<CartItem> items;
}

