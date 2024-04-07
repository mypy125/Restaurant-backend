package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
