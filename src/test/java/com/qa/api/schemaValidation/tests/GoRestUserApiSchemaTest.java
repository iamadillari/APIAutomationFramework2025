package com.qa.api.schemaValidation.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for validating API schemas of GoRest User APIs.
 * Contains methods to validate the schema of GET and POST requests.
 */
public class GoRestUserApiSchemaTest extends BaseTest {

    /**
     * Test to validate the schema of the 'GET Users' API.
     * Sets the bearer token, sends a GET request, and validates the response schema.
     */
    @Test
    public void getUsersAPISchemaTest() {
        // Set the bearer token for authentication
        ConfigManager.set("bearerToken", "eab5554f90d4c5e213aea6bdfed3465ed6250b3a43048f6b128d9a5a0f9c8c72");

        // Send a GET request to fetch users
        Response getUsersResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Validate the response schema using the provided schema file
        Assert.assertTrue(SchemaValidator.validateSchema(getUsersResponse, "schema/getUsersSchema_GoRest.json"),
                "Validating the Schema for 'GOREST GET USERS API'");
    }

    /**
     * Test to validate the schema of the 'Create User' API.
     * Sets the bearer token, sends a POST request with user data, and validates the response schema.
     */
    @Test
    public void createASingleUserAPISchemaTest() {
        // Set the bearer token for authentication
        ConfigManager.set("bearerToken", "eab5554f90d4c5e213aea6bdfed3465ed6250b3a43048f6b128d9a5a0f9c8c72");

        // Create a User object with test data
        User user = User.builder()
                .name("Aman")
                .email(StringUtils.generateRandomEmailId())
                .gender("male")
                .status("inactive")
                .build();

        // Send a POST request to create a user
        Response createAUserResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Validate the response schema using the provided schema file
        Assert.assertTrue(SchemaValidator.validateSchema(createAUserResponse, "schema/createAUserSchema_GoRest.json"),
                "Validating the Schema for 'GOREST CREATE USER API'");
    }
}