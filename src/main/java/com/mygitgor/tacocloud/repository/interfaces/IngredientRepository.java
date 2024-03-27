package com.mygitgor.tacocloud.repository.interfaces;

import com.mygitgor.tacocloud.domain.taco.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);

}
