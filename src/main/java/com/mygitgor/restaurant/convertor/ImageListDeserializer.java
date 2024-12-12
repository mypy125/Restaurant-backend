package com.mygitgor.restaurant.convertor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonComponent
public class ImageListDeserializer extends JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<String> images = new ArrayList<>();
        ArrayNode node = p.getCodec().readTree(p);

        for (int i = 0; i < node.size(); i++) {
            String imageUrl = node.get(i).asText();
            images.add(imageUrl);
        }
        return images;
    }
}
