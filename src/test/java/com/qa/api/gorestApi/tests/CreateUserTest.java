package com.qa.api.gorestApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * CreateUserTest class contains test cases for creating users using different input formats.
 * It extends the BaseTest class to utilize common test setup and utilities.
 */
@Epic("EpicGOREST") // Specifies the epic associated with these tests in Allure reports.
@Story("Creating of GoRest Users") // Specifies the story associated with these tests in Allure reports.
public class CreateUserTest extends BaseTest {

    /**
     * Test to create a user using a User object.
     * Verifies the response status code, name, and ID in the response.
     */
    @Description("Creation of a User Test using a DTO/POJO for GoRest API") // Provides a description for the test in Allure reports.
    @Owner("Adil Lari") // Specifies the owner of the test in Allure reports.
    @Severity(SeverityLevel.CRITICAL) // Marks the severity level of the test in Allure reports.
    @Test
    public void createAUserTest() {
        // Generate a random email ID for testing purposes
        Allure.step("Generate a random email ID for testing purposes");
        String emailId = StringUtils.generateRandomEmailId();
        Allure.step("Generated a random email ID: " + emailId);

        // Create a User object with test data
        Allure.step("Create a User object with test data");
        User user = new User(null, "Aditya", emailId, "male", "active");

        // Send a POST request to create the user
        Allure.step("Send a POST request to create the user");
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert the response status code is 201 (Created)
        Allure.step("Assert the response status code is 201 (Created)");
        Assert.assertEquals(actualResponse.statusCode(), 201);

        // Assert the response contains the correct name
        Allure.step("Assert the response contains the correct name");
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Aditya");

        // Assert the response contains a non-null ID
        Allure.step("Assert the response contains a non-null ID");
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"));
    }

    /**
     * Test to create a user using a JSON string.
     * Verifies the response status code, name, and ID in the response.
     */
    @Description("Creation of a User Test using JSON String as body for the GoRest API") // Provides a description for the test in Allure reports.
    @Owner("Adil Lari") // Specifies the owner of the test in Allure reports.
    @Severity(SeverityLevel.NORMAL) // Marks the severity level of the test in Allure reports.
    @Test
    public void createAUserWithJsonStringTest() {
        // Generate a random email ID for testing purposes
        Allure.step("Generate a random email ID for testing purposes");
        String emailId = StringUtils.generateRandomEmailId();
        Allure.step("Generated a random email ID: " + emailId);

        // Define a JSON string representing the user
        Allure.step("Define a JSON string representing the user");
        String user = "{\n" +
                "  \"name\": \"Aditya\",\n" +
                "  \"email\": \"" + emailId + "\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"status\": \"inactive\"\n" +
                "}";

        // Send a POST request to create the user
        Allure.step("Send a POST request to create the user");
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert the response status code is 201 (Created)
        Allure.step("Assert the response status code is 201 (Created)");
        Assert.assertEquals(actualResponse.statusCode(), 201);

        // Assert the response contains the correct name
        Allure.step("Assert the response contains the correct name");
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Aditya");

        // Assert the response contains a non-null ID
        Allure.step("Assert the response contains a non-null ID");
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"));
    }

    /**
     * Test to create a user using a static JSON file.
     * Verifies the response status code, name, and ID in the response.
     */
    @Description("Creation of a User Test using a JSON file for the GoRest API") // Provides a description for the test in Allure reports.
    @Owner("Adil Lari") // Specifies the owner of the test in Allure reports.
    @Severity(SeverityLevel.CRITICAL) // Marks the severity level of the test in Allure reports.
    @Test
    public void createAUserWithStaticJsonFileTest() throws IOException {
        // Generate a random email ID for testing purposes
        Allure.step("Generate a random email ID for testing purposes");
        String emailId = StringUtils.generateRandomEmailId();
        Allure.step("Generated a random email ID: " + emailId);

        // Load the user data from a static JSON file
        Allure.step("Load the user data from a static JSON file");
        String userFile = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));

        // Replace the email placeholder with the generated email ID
        Allure.step("Replace the email placeholder with the generated email ID");
        String updatedJsonFile = userFile.replace("{{email}}", emailId);

        // Send a POST request to create the user
        Allure.step("Send a POST request to create the user");
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                updatedJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert the response status code is 201 (Created)
        Allure.step("Assert the response status code is 201 (Created)");
        Assert.assertEquals(actualResponse.statusCode(), 201);

        // Assert the response contains the correct name
        Allure.step("Assert the response contains the correct name");
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Naveen");

        // Assert the response contains a non-null ID
        Allure.step("Assert the response contains a non-null ID");
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"));
    }
}