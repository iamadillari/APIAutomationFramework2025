package com.qa.api.contactsApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsAPICredentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

/**
 * ContactsApiTest class contains test methods to validate the functionality of the Contacts API.
 * It includes generating a token, fetching all contacts, and creating a new contact.
 */
public class ContactsApiTest extends BaseTest {

    // Token used for authentication in API requests
    private String token;

    // ID of the contact created during the test
    String contactId;

    /**
     * Generates a bearer token for authentication by sending a POST request with valid credentials.
     * The token is stored in the ConfigManager for use in subsequent API requests.
     */
    @BeforeMethod
    public void generateToken() {
        // Create credentials for the Contacts API
        ContactsAPICredentials contactsAPICredentials =
                ContactsAPICredentials
                        .builder().email("mayanksingh1234@gmail.com").password("password").build();

        // Send a POST request to generate the token
        Response tokenResponse = restClient.post(CONTACTSAPI_BASEURL, CONTACTSAPI_LOGIN_ENDPOINT,
                contactsAPICredentials, null, null, AuthType.NO_AUTH, ContentType.JSON);

        // Validate the token response
        Assert.assertNotNull(tokenResponse.jsonPath().getString("token"), "Validating the TOKEN value is not NULL");
        Assert.assertEquals(tokenResponse.statusCode(), 200, "Validating the status code is 200");
        Assert.assertTrue(tokenResponse.statusLine().contains("OK"), "Validating the status line is OK");

        // Store the token for future use
        token = tokenResponse.jsonPath().getString("token");
        ConfigManager.set("bearerToken", token);
    }

    /**
     * Test method to validate fetching all contacts.
     * Sends a GET request to retrieve all contacts and validates the response status code.
     */
    @Test
    public void getAllContactsTest() {
        // Send a GET request to fetch all contacts
        Response getAllContactsResponse = restClient.get(CONTACTSAPI_BASEURL, CONTACTSAPI_CREATE_CONTACTS_ENDPOINT,
                null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Validate the response status code
        Assert.assertEquals(getAllContactsResponse.statusCode(), 200, "Validating the status code is 200");
    }

    /**
     * Test method to validate creating a new contact.
     * Sends a POST request with contact details and validates the response status code and contact ID.
     */
    @Test
    public void createAContactTest() {
        // Load contact details from a JSON file
        File contactDetailsFile = new File("src/test/resources/jsons/Contacts.json");

        // Send a POST request to create a new contact
        Response contactCreationResponse = restClient.post(CONTACTSAPI_BASEURL, CONTACTSAPI_CREATE_CONTACTS_ENDPOINT,
                contactDetailsFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

        // Validate the response status code and contact ID
        Assert.assertEquals(contactCreationResponse.statusCode(), 201, "Validating the status code is 201");
        Assert.assertTrue(contactCreationResponse.statusLine().contains("Created"), "Validating the status line is CREATED");
        Assert.assertNotNull(contactCreationResponse.jsonPath().getString("_id"), "Validating the ID value is not NULL");

        // Store the contact ID for future use
        contactId = contactCreationResponse.jsonPath().getString("_id");
    }

    // TODO: write more possible Test case for remaining scenarios for the ContactsAPI, take reference from Postman collection

}