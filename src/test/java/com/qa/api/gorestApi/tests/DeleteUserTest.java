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
 * DeleteUserTest class contains test methods to validate the functionality of deleting a user
 * in the GoRest API. It includes creating a user, updating the user, and verifying the deletion.
 */
public class DeleteUserTest extends BaseTest {

    // Randomly generated email ID for creating a new user
    String randomEmailId = StringUtils.generateRandomEmailId();

    // Updated final status as active for modifying the user
    String finalStatus = "active";

    /**
     * Test method to validate the delete user functionality.
     * The test performs the following steps:
     * 1. Creates a new user with test data.
     * 2. Fetches the created user's details to validate the creation.
     * 3. Updates the user's status and validates the update.
     * 4. Deletes the user and validates the deletion.
     * 5. Verifies that the deleted user no longer exists in the system.
     */
    @Test
    public void deleteUserTest() {

        // 1. Create a User object with test data
        User user = User.builder().name("Rahul").email(randomEmailId).gender("Male").status("inactive").build();
        Response actualResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(actualResponse.statusCode(), 201, "Validating Status code is 201");
        Assert.assertEquals(actualResponse.jsonPath().getString("name"), "Rahul", "Validating the user's name");
        Assert.assertNotNull(actualResponse.jsonPath().getString("id"), "Validating the user's id");
        String userId = actualResponse.jsonPath().getString("id");

        // 2. Get the created User details
        Response actualGetResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(actualGetResponse.statusLine().contains("OK"), "Validating the Status Line is OK");
        Assert.assertNotNull(actualGetResponse.body(), "Validating that body is not NULL");
        Assert.assertTrue(actualGetResponse.asString().contains(userId), "Validating response contains same userID which was created initially");

        // 3. Update the above user
        user.setStatus(finalStatus); // Modify the status of the previously created user
        Response actualUpdatedResponse = restClient.put(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(actualUpdatedResponse.jsonPath().getString("id"), userId, "Validating the update is done for the same UserId which was created initially");
        Assert.assertEquals(actualUpdatedResponse.jsonPath().getString("name"), "Rahul", "Validating the name after update");
        Assert.assertEquals(actualUpdatedResponse.jsonPath().getString("status"), finalStatus, "Validating the response if status is successfully updated as ACTIVE");

        // 4. Delete the above user
        Response deletedResponse = restClient.delete(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(deletedResponse.statusLine().contains("No Content"), "Validating the Status Line is NO CONTENT");

        // 5. Get call to check if User does not exist in the system.
        Response finalGetResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(finalGetResponse.statusCode(), 404, "Validating Status code is 404");
        Assert.assertTrue(finalGetResponse.statusLine().contains("Not Found"), "Validating the status line of deleted customer is NOT FOUND");
        Assert.assertEquals(finalGetResponse.jsonPath().getString("message"),
                "Resource not found", "Validating the response message after deleting the user");
    }
}