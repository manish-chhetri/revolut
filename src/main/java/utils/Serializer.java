package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Serializer {

    public static JsonNode readJson(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON");
        }
    }
    public static String toJson(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException("Unable to parse json:: " + jpe.getMessage());
        }
    }
}
