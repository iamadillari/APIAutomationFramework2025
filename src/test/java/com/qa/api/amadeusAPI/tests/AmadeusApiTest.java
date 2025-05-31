package com.qa.api.amadeusAPI.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Test class for validating the Amadeus API endpoints.
 * Extends the BaseTest class to utilize common setup and configurations.
 * Contains methods for generating OAuth tokens and testing flight status details.
 */
public class AmadeusApiTest extends BaseTest {

    private String accessToken;
    private String grantType = ConfigManager.get("grantType");
    private String clientID = ConfigManager.get("clientID");
    private String clientSecret = ConfigManager.get("clientSecret");

    /**
     * Generates an OAuth token before each test method.
     * Sends a POST request to the Amadeus API to retrieve the access token.
     * Validates that the access token is not null and stores it for further use.
     */
    @BeforeMethod
    public void getOAuthToken() {
        // Sending POST request to fetch OAuth token
        Response tokenResponse = restClient.post(AMADEUSAPI_BASEURL, AMADEUSAPI_ENDPOINT,
                clientID, clientSecret, grantType, ContentType.URLENC);

        // Extracting and validating the access token
        accessToken = tokenResponse.jsonPath().getString("access_token");
        Assert.assertNotNull(accessToken, "Validating Access Token is NOT NULL");
        System.out.println("Access Token: " + accessToken);

        // Storing the access token in the configuration manager
        ConfigManager.set("bearerToken", accessToken);
        System.out.println("<<<<<<<<<<<<<<Access Token Generated>>>>>>>>>>>>>>");
    }

    /**
     * Test method to validate the retrieval of flight status details.
     * Sends a GET request to the Amadeus API with query parameters.
     * Validates the response status code and ensures the response body is not null.
     */
    @Test
    public void getFlightStatusDetailsTest() {
        System.out.println("<<<<<<<<<Start of Testcase>>>>>>>>>");

        // Defining query parameters for the API request
        Map<String, String> queryParams = Map.of("origin", "PAR", "maxPrice", "200");

        // Sending GET request to fetch flight status details
        Response flightDestinationsResponse = restClient.get(AMADEUSAPI_BASEURL, AMADEUSAPI_FLIGHTDESTINATIONS_ENDPOINT,
                queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Validating the response status code and body
        Assert.assertEquals(flightDestinationsResponse.statusCode(), 200, "Validating status code is 200");
        Assert.assertNotNull(flightDestinationsResponse.getBody(), "Validating response body is not Null");
        System.out.println("<<<<<<<<<End of Testcase>>>>>>>>>");
    }
}