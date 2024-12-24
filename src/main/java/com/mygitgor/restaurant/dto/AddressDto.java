package com.mygitgor.restaurant.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
}
