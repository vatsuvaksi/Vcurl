package com.vatsuvaksi.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFactory {

    // Method to convert object to JSON string using Jackson
    public static String convertObjectToJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    // Method to convert JSON string to Java object using Jackson
    public static <T> T convertJsonToObject(String jsonString, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, clazz);
    }
}
