package com.mygitgor.restaurant.request;

import com.mygitgor.restaurant.domain.Address;
import com.mygitgor.restaurant.dto.AddressDto;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private AddressDto deliveryAddress;
}
