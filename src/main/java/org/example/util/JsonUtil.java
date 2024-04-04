package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @Author junxiao
 **/
@Component
public class JsonUtil {
    @Autowired
    private ObjectMapper objectMapper;
    private static ObjectMapper JSON_OBJECT_MAPPER;

    @PostConstruct
    public void initJsonUtil() {
        JSON_OBJECT_MAPPER = objectMapper;
    }

    public static String toJsonStr(Object data) throws JsonProcessingException {
        return JSON_OBJECT_MAPPER.writeValueAsString(data);
    }

    public static String toPrettyJsonStr (Object data) {
        JsonNode jsonNode = JSON_OBJECT_MAPPER.valueToTree(data);
        return jsonNode.toPrettyString();
    }

    public static <T> ArrayNode toJsonArray(List<T> objList) {
        return JSON_OBJECT_MAPPER.valueToTree(objList);
    }

    public static <T> T getInstance(Class<T> clazz, String jsonStr) throws JsonProcessingException {
        return JSON_OBJECT_MAPPER.readValue(jsonStr, clazz);
    }

    public static JsonNode getJsonNode(String jsonStr) throws JsonProcessingException {
        return JSON_OBJECT_MAPPER.readTree(jsonStr);
    }

    public static <T> List<T> getObjList(ArrayNode arrayNode) throws IOException {
        ObjectReader arrayReadr = JSON_OBJECT_MAPPER.readerFor(new TypeReference<List<T>>() {
        });
        List<T> objList = arrayReadr.readValue(arrayNode);
        return objList;
    }
}
