package com.mygitgor.tacocloud.repository.interfaces;

import com.mygitgor.tacocloud.domain.taco.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TacoRepository {
    Taco save(Taco taco);
}
