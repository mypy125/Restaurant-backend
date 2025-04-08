package com.mygitgor.restaurant.model.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseModelId{
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private Long userId;
}

