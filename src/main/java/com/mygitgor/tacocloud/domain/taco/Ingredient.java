package com.mygitgor.tacocloud.domain.taco;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Data
public class Ingredient {
    @Id
    private final String id;
    private final String name;

    @Enumerated(EnumType.STRING)
    private final Type type;
}
