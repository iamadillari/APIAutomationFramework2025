package com.qa.api.gorestApi.tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * GetUserTest class contains test cases for retrieving user details from the API.
 * It extends the BaseTest class to utilize common test setup and utilities.
 */
public class GetUserTest extends BaseTest {

    /**
     * Test to retrieve all users.
     * Verifies that the response status line contains "OK" and the response body is not null.
     */
    @Test
    public void getAllUsersTest() {
        // Send a GET request to retrieve all users
        Response actualResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert that the response status line contains "OK"
        Assert.assertTrue(actualResponse.statusLine().contains("OK"));

        // Assert that the response body is not null
        Assert.assertNotNull(actualResponse.body());
        ChainTestListener.log("Get All Users Test");
    }

    /**
     * Test to retrieve all users with specific query parameters.
     * Verifies that the response status line contains "OK" and the response body is not null.
     */
    @Test
    public void getAllUsersWithQueryParamsTest() {
        // Define query parameters for the request
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("status", "active");
        queryParams.put("gender", "male");

        // Send a GET request with query parameters to retrieve users
        Response actualResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert that the response status line contains "OK"
        Assert.assertTrue(actualResponse.statusLine().contains("OK"));

        // Assert that the response body is not null
        Assert.assertNotNull(actualResponse.body());
        ChainTestListener.log("Get All Users With Query Parameters Test");
    }

    /**
     * Test to retrieve a single user's details by their user ID.
     * Verifies that the response status line contains "OK", the response body is not null,
     * and the user ID in the response matches the requested user ID.
     */
    @Test
    public void getSingleUserTest() {
        // Define the user ID to retrieve
        String userID = "7908869";

        // Send a GET request to retrieve the user's details
        Response actualResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userID,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Assert that the response status line contains "OK"
        Assert.assertTrue(actualResponse.statusLine().contains("OK"));

        // Assert that the response body is not null
        Assert.assertNotNull(actualResponse.body());

        // Assert that the user ID in the response matches the requested user ID
        Assert.assertEquals(actualResponse.jsonPath().getString("id"), userID);
        ChainTestListener.log("Get Single User Test");
    }

}