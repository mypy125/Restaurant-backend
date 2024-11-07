package com.mygitgor.restaurant.request;

import com.mygitgor.restaurant.domain.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
