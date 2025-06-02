package com.qa.api.gorestApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;
import com.qa.api.utils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for creating and retrieving a user using deserialization.
 * Contains methods to validate user creation and retrieval via API calls.
 */
public class GetAUserWithDeserializationTest extends BaseTest {

    /**
     * Stores the ID of the created user for further validation.
     */
    protected String userId;

    /**
     * Test to create a user and validate its retrieval using deserialization.
     * Steps:
     * 1. Create a user using a POST request.
     * 2. Validate the response status code and extract the user ID.
     * 3. Retrieve the user using a GET request.
     * 4. Validate the response status code and deserialize the response body.
     * 5. Validate the deserialized user details.
     */
    @Test
    public void createAUserTest() {
        // Create a User object with test data
        User user = User.builder()
                .name("Adil")
                .email(StringUtils.generateRandomEmailId())
                .gender("male")
                .status("active")
                .build();

        // Send a POST request to create the user
        Response postResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Extract the user ID from the response
        userId = postResponse.jsonPath().getString("id");

        // Validate the response status code is 201 (Created)
        Assert.assertEquals(postResponse.statusCode(), 201, "Validating the status code is 200");

        // Send a GET request to retrieve the created user
        Response getResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Validate the response status code is 200 (OK)
        Assert.assertEquals(getResponse.statusCode(), 200, "Validating status code is 200");

        // Deserialize the response body into a User object
        User userResponseBody = JsonUtils.deserialize(getResponse, User.class);

        // Validate the deserialized user details
        Assert.assertNotNull(userResponseBody.getId(), "Validating that ID of the user is not NULL");
        Assert.assertEquals(userResponseBody.getName(), user.getName(), "Validating the Name of User");
        Assert.assertEquals(userResponseBody.getStatus(), user.getStatus(), "Validating the Status of User");
    }

    // TODO: Practice with Deserialization with User with Array (for multiple users)
}