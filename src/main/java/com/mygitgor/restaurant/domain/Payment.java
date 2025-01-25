package com.mygitgor.restaurant.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment extends BaseEntity{
    private String paymentMethod;
    private String paymentStatus;
    private Date paymentDate;
    private Long amount;

    @OneToOne(mappedBy = "payment")
    private Order order;
}

