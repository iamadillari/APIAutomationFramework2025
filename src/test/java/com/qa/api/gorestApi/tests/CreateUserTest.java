package com.qa.api.gorestApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * CreateUserTest class contains test cases for creating users using different input formats.
 * It extends the BaseTest class to utilize common test setup and utilities.
 */
public class CreateUserTest extends BaseTest {

    // Generate a random email ID for testing purposes
    String emailId = StringUtils.generateRandomEmailId();

    /**
     * Test to create a user using a User object.
     * Verifies the response status code, name, and ID in the response.
     */
    @Test
    public void createAUserTest() {
        // Create a User object with test data
        User user = new User(null,"Aditya", emailId, "male", "active");

        // Send a POST request to create the user
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert the response status code is 201 (Created)
        Assert.assertEquals(actualResponse.statusCode(), 201);

        // Assert the response contains the correct name
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Aditya");

        // Assert the response contains a non-null ID
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"));
    }

    /**
     * Test to create a user using a JSON string.
     * Verifies the response status code, name, and ID in the response.
     */
    @Test
    public void createAUserWithJsonStringTest() {
        // Define a JSON string representing the user
        String user = "{\n" +
                "\"name\": \"Aditya\",\n" +
                "\"email\": \"dsada43245sdf@example.com\",\n" +
                "\"gender\": \"male\",\n" +
                "\"status\": \"inactive\"\n" +
                "}";

        // Send a POST request to create the user
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert the response status code is 201 (Created)
        Assert.assertEquals(actualResponse.statusCode(), 201);

        // Assert the response contains the correct name
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Aditya");

        // Assert the response contains a non-null ID
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"));
    }

    /**
     * Test to create a user using a static JSON file.
     * Verifies the response status code, name, and ID in the response.
     */
    @Test
    public void createAUserWithStaticJsonFileTest() {
        // Load the user data from a static JSON file
        File userFile = new File("src/test/resources/jsons/user.json");

        // Send a POST request to create the user
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                userFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert the response status code is 201 (Created)
        Assert.assertEquals(actualResponse.statusCode(), 201);

        // Assert the response contains the correct name
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Naveen");

        // Assert the response contains a non-null ID
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"));
    }
}