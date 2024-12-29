package com.mygitgor.restaurant.convertor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class IngredientItemListStringDeserializer  extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<String> ingredients = new ArrayList<>();
        ArrayNode arrayNode = p.getCodec().readTree(p);

        for (JsonNode node : arrayNode) {
            if (node.isTextual()) {
                ingredients.add(node.asText());
            }
        }
        return ingredients;
    }
}
