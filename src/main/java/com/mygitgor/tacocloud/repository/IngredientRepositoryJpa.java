package com.mygitgor.tacocloud.repository;

import com.mygitgor.tacocloud.domain.taco.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepositoryJpa extends CrudRepository<Ingredient,String> {

}
