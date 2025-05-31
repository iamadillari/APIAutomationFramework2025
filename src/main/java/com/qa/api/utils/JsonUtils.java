package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * Utility class for JSON operations such as deserialization.
 * Provides methods to handle JSON responses and map them to Java objects.
 */
public class JsonUtils {

    // ObjectMapper instance used for JSON processing
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Deserializes a JSON response into a specified Java object.
     *
     * @param response The Response object containing the JSON data.
     * @param targetClass The target class to which the JSON should be deserialized.
     * @param <T> The type of the target class.
     * @return An instance of the target class populated with data from the JSON response.
     * @throws RuntimeException if deserialization fails.
     */
    public static <T> T deserialize(Response response, Class<T> targetClass) {
        try {
            return objectMapper.readValue(response.getBody().asString(), targetClass);
        } catch (Exception e) {
            throw new RuntimeException("Deserialization is Failed!!! " + targetClass.getName());
        }
    }

}