package com.qa.api.basicAuth.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * BasicAuthTest class contains test methods to validate the functionality of Basic Authentication
 * using the HerokuApp API. It verifies that the API responds correctly when valid credentials are provided.
 */
public class BasicAuthTest extends BaseTest {

    /**
     * Test method to validate Basic Authentication.
     * The test performs the following steps:
     * 1. Sends a GET request to the Basic Auth endpoint with valid credentials.
     * 2. Validates that the response status code is 200.
     * 3. Validates that the response body contains the success message.
     */
    @Test
    public void basicAuthTest() {
        // Send a GET request with Basic Authentication
        Response getBasicAuthResponse = restClient.get(HEROKUAPP_BASEURL, HEROKUAPP_BASICAUTH_ENDPOINT,
                null, null, AuthType.BASIC_AUTH, ContentType.JSON);

        // Validate the response status code
        Assert.assertEquals(getBasicAuthResponse.statusCode(), 200, "Validating the Status code is 200");

        // Validate the response body contains the success message
        Assert.assertTrue(getBasicAuthResponse.getBody().asString().contains("Congratulations! You must have the proper credentials."),
                "Validating the message 'Congratulations! You must have the proper credentials.' in the Response body");
    }

}