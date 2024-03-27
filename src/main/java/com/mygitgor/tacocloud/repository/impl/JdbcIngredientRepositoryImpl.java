package com.mygitgor.tacocloud.repository.impl;

import com.mygitgor.tacocloud.domain.taco.Ingredient;
import com.mygitgor.tacocloud.domain.taco.Type;
import com.mygitgor.tacocloud.repository.interfaces.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepositoryImpl implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;
    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @SneakyThrows
    private Ingredient mapRowToIngredient(ResultSet resultSet, int i) {
        return new Ingredient(resultSet.getString("id"),resultSet.getString("name"),
                Type.valueOf(resultSet.getString("type")));
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into Ingredient (id,name,type) values (?,?,?)",
                ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }
}
