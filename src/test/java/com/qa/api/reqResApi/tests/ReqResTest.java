package com.qa.api.reqResApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for ReqRes API.
 * Extends the BaseTest class to utilize common setup and configurations.
 * Contains test methods for validating ReqRes API endpoints.
 */
public class ReqResTest extends BaseTest {

    /**
     * Test method to validate the "Get Users" API endpoint.
     * Sends a GET request to fetch users from page 2 and validates the response status code.
     */
    @Test
    public void getUsersTest(){
        // Query parameters for the API request
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("page", "2");

        // Sending GET request and storing the response
        Response getUsersResponse = restClient.get(REQRESAPI_BASEURL, REQRES_USERS_ENDPOINT,
                queryParam, null, AuthType.NO_AUTH, ContentType.JSON);

        // Asserting that the status code is 200
        Assert.assertEquals(getUsersResponse.statusCode(),200, "Validating the Status code is 200");
    }

}