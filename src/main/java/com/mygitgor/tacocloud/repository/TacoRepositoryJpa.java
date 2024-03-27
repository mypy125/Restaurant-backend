package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.taco.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepositoryJpa extends PagingAndSortingRepository<Taco, Long> {
}
