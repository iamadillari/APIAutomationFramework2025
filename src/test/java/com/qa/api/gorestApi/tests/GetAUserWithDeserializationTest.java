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

public class GetAUserWithDeserializationTest extends BaseTest {

    protected String userId;

    @Test
    public void createAUserTest() {
        User user = User.builder().name("Adil").email(StringUtils.generateRandomEmailId()).gender("male").status("active").build();
        Response postResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        userId = postResponse.jsonPath().getString("id");
        Assert.assertEquals(postResponse.statusCode(), 201, "Validating the status code is 200");

        Response getResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS + "/" + userId,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(getResponse.statusCode(), 200, "Validating status code is 200");
        User userResponseBody = JsonUtils.deserialize(getResponse, User.class);
        Assert.assertNotNull(userResponseBody.getId(), "Validating that ID of the user is not NULL");
        Assert.assertEquals(userResponseBody.getName(), user.getName(), "Validating the Name of User");
        Assert.assertEquals(userResponseBody.getStatus(), user.getStatus(), "Validating the Status of User");
    }

    //TODO: Practice with Deserialization with User with Array (for multiple users)
}
