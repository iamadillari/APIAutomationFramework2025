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

public class GoRestUserApiSchemaTest extends BaseTest {

    @Test
    public void getUsersAPISchemaTest(){
        ConfigManager.set("bearerToken", "eab5554f90d4c5e213aea6bdfed3465ed6250b3a43048f6b128d9a5a0f9c8c72");
        Response getUsersResponse = restClient.get(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                null,null,AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(SchemaValidator.validateSchema(getUsersResponse, "schema/getUsersSchema_GoRest.json"),
                "Validating the Schema for 'GOREST GET USERS API'");
    }

    @Test
    public void createASingleUserAPISchemaTest(){
        ConfigManager.set("bearerToken", "eab5554f90d4c5e213aea6bdfed3465ed6250b3a43048f6b128d9a5a0f9c8c72");
        User user = User.builder().name("Aman").email(StringUtils.generateRandomEmailId()).gender("male").status("inactive").build();
        Response createAUserResponse = restClient.post(GORESTAPI_BASEURL, GORESTAPI_ENDPOINT_USERS,
                user,null,null,AuthType.BEARER_TOKEN,ContentType.JSON);
        Assert.assertTrue(SchemaValidator.validateSchema(createAUserResponse,"schema/createAUserSchema_GoRest.json"),
                "Validating the Schema for 'GOREST CREATE USER API'");
    }
}