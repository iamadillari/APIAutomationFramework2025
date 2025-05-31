package com.qa.api.gorestApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * UpdateUserTest class contains test methods to validate the functionality of updating a user
 * in the GoRest API. It includes creating a user, fetching the user details, and updating the user.
 */
public class UpdateUserTest extends BaseTest {

    // Randomly generated email ID for creating a new user
    String emailId = StringUtils.generateRandomEmailId();

    // Updated final status as active for modifying the user
    String finalStatus = "active";

    /**
     * Test method to validate the update user functionality.
     * The test performs the following steps:
     * 1. Creates a new user with test data.
     * 2. Fetches the created user's details to validate the creation.
     * 3. Updates the user's status and validates the update.
     */
    @Test
    public void updateUserTest() {
        // 1. Create a User object with test data
        User user = User.builder().name("Aman").email(emailId).gender("male").status("inactive").build();
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(actualResponse.statusCode(), 201, "Validating Status code is 201");
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Aman", "Validating the user's name");
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"), "Validating the user's id");
        String userId = actualResponse.jsonPath().getString("id");

        // 2. Get the created User details
        Response actualGetResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(actualGetResponse.statusLine().contains("OK"), "Validating the Status Line is OK");
        Assert.assertNotNull(actualGetResponse.body(), "Validating that body is not NULL");
        Assert.assertEquals(actualGetResponse.jsonPath().getString("id"), userId, "Validating the User Id");

        // 3. Update the above user
        user.setStatus(finalStatus); // Modify the status of the previously created user
        Response actualUpdatedResponse = restClient.put(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(actualUpdatedResponse.jsonPath().getString("id"), userId, "Validating the update is done for the same UserId which was created initially");
        Assert.assertEquals(actualUpdatedResponse.jsonPath().getString("name"), "Aman", "Validating the name after update");
        Assert.assertEquals(actualUpdatedResponse.jsonPath().getString("status"), finalStatus, "Validating status is updated successfully as Active for the user");
    }
}