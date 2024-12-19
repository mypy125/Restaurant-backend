package com.mygitgor.restaurant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String paymentMethod;
    private String paymentStatus;
    private Date paymentDate;
    private Long amount;

    @OneToOne(mappedBy = "payment")
    private Order order;
}

