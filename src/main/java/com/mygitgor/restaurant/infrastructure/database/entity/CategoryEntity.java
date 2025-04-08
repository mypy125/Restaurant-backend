package com.mygitgor.restaurant.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity{
    private String name;
    @JsonIgnore
    @ManyToOne
    private RestaurantEntity restaurant;
}
