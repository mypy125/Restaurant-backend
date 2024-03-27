package com.mygitgor.tacocloud.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygitgor.tacocloud.domain.taco.Ingredient;
import com.mygitgor.tacocloud.domain.taco.Taco;
import com.mygitgor.tacocloud.repository.interfaces.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTacoRepositoryImpl implements TacoRepository {
    private final SimpleJdbcInsert tacoInserter;
    private final SimpleJdbcInsert tacoIngredientInserter;
    private final ObjectMapper objectMapper;

    @Autowired
    public JdbcTacoRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.tacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco").usingGeneratedKeyColumns("id");
        this.tacoIngredientInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Ingredients");
        this.objectMapper = new ObjectMapper();

    }
    @Override
    public Taco save(Taco taco) {
        taco.setCreatedAt(new Date());
        long tacoId = saveTacoDetails(taco);
        taco.setId(tacoId);
        List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient : ingredients) {
            saveIngredientsToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private void saveIngredientsToTaco(Ingredient ingredient, long tacoId) {
        Map<String, Object> values = new HashMap<>();
        values.put("taco", tacoId);
        values.put("ingredient", ingredient.getId());
        tacoIngredientInserter.execute(values);
    }

    private long saveTacoDetails(Taco taco) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
        values.put("createdAt", taco.getCreatedAt());
        long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
        return tacoId;
    }
}
