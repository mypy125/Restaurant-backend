package com.mygitgor.restaurant.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity extends BaseEntity{
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
