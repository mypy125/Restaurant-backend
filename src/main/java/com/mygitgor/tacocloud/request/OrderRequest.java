package com.mygitgor.tacocloud.request;

import com.mygitgor.tacocloud.domain.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
