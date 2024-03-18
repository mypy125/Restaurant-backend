package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.taco.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {

}
