package com.mygitgor.restaurant.model.domain;

import com.mygitgor.restaurant.infrastructure.database.entity.CartItemEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseModelId{
    private Long customerId;
    private Long total;
    private List<CartItemEntity> items;
}

