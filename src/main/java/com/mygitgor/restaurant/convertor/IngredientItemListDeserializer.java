package com.mygitgor.restaurant.convertor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mygitgor.restaurant.domain.IngredientItem;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class IngredientItemListDeserializer extends JsonDeserializer<List<IngredientItem>> {
    @Override
    public List<IngredientItem> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<IngredientItem> ingredientItems = new ArrayList<>();
        ArrayNode arrayNode = p.getCodec().readTree(p);

        for (JsonNode node : arrayNode) {
            if (node.isTextual()) {
                continue;
            }

            IngredientItem ingredientItem = new IngredientItem();

            if (node.has("id") && !node.get("id").isNull()) {
                ingredientItem.setId(node.get("id").asLong());
            } else {
                throw new IllegalArgumentException("Ingredient item must have an 'id' field.");
            }

            if (node.has("name") && !node.get("name").isNull()) {
                ingredientItem.setName(node.get("name").asText());
            } else {
                throw new IllegalArgumentException("Ingredient item must have a 'name' field.");
            }

            ingredientItems.add(ingredientItem);
        }
        return ingredientItems;
    }
}




