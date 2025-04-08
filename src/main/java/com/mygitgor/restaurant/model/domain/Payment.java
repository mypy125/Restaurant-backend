package com.mygitgor.restaurant.model.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseModelId{
    private String paymentMethod;
    private String paymentStatus;
    private Date paymentDate;
    private Long amount;
}
