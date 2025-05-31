package com.qa.api.utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

/**
 * Utility class for validating and extracting data from JSON responses using JsonPath.
 * Provides methods to read specific values, lists, and lists of maps from a JSON response.
 */
public class JsonPathValidatorUtil {

    /**
     * Reads a value from the JSON response using the specified JsonPath.
     *
     * @param response The API response containing the JSON body.
     * @param jsonPath The JsonPath expression to extract the value.
     * @param <T>      The type of the value to be returned.
     * @return The value extracted from the JSON response.
     */
    public static <T> T read(Response response, String jsonPath) {
        return JsonPath.parse(response.getBody().asString()).read(jsonPath);
    }

    /**
     * Reads a list of values from the JSON response using the specified JsonPath.
     *
     * @param response The API response containing the JSON body.
     * @param jsonPath The JsonPath expression to extract the list of values.
     * @param <T>      The type of the values in the list.
     * @return A list of values extracted from the JSON response.
     */
    public static <T> List<T> readList(Response response, String jsonPath) {
        return JsonPath.parse(response.getBody().asString()).read(jsonPath);
    }

    /**
     * Reads a list of maps from the JSON response using the specified JsonPath.
     *
     * @param response The API response containing the JSON body.
     * @param jsonPath The JsonPath expression to extract the list of maps.
     * @param <T>      The type of the keys and values in the maps.
     * @return A list of maps extracted from the JSON response.
     */
    public static <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {
        return JsonPath.parse(response.getBody().asString()).read(jsonPath);
    }
}