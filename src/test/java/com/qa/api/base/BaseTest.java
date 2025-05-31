package com.qa.api.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

/**
 * Base test class for API testing.
 * Provides common setup and configuration for API tests, including base URLs and endpoints.
 */
@Listeners(ChainTestListener.class)
public class BaseTest {

    /** Instance of RestClient used for making API requests. */
    protected RestClient restClient;

    // BaseUrl and endpoints for GOREST API
    /** Base URL for the GOREST API. */
    protected final static String GORESTAPI_BASEURL = ConfigManager.get("baseUrl_GoRestApi");
    /** Endpoint for the GOREST API users resource. */
    protected final static String GORESTAPI_ENDPOINT_USERS = ConfigManager.get("endPoint_Users_GoRestApi");

    // BaseUrl and endpoints for CONTACTS API
    /** Base URL for the CONTACTS API. */
    protected final static String CONTACTSAPI_BASEURL = ConfigManager.get("baseUrl_ContactsApi");
    /** Endpoint for the CONTACTS API login resource. */
    protected final static String CONTACTSAPI_LOGIN_ENDPOINT = ConfigManager.get("endPoint_contactsApi_Login");
    /** Endpoint for creating contacts in the CONTACTS API. */
    protected final static String CONTACTSAPI_CREATE_CONTACTS_ENDPOINT = ConfigManager.get("endPoint_contactsApi_createContact");

    // BaseUrl and endpoints for REQRES API
    /** Base URL for the REQRES API. */
    protected final static String REQRESAPI_BASEURL = ConfigManager.get("baseUrl_ReqResApi");
    /** Endpoint for the REQRES API users resource. */
    protected final static String REQRES_USERS_ENDPOINT = ConfigManager.get("endPoint_ReqResApi_users");

    // BaseUrl and endpoints for HEROKUAPP API
    /** Base URL for the HEROKUAPP API. */
    protected final static String HEROKUAPP_BASEURL = ConfigManager.get("baseUrl_herokuApp");
    /** Endpoint for the HEROKUAPP API basic authentication resource. */
    protected final static String HEROKUAPP_BASICAUTH_ENDPOINT = ConfigManager.get("endPoint_herokuApp_basicAuth");

    // BaseUrl and endpoints for FAKESTORE API
    /** Base URL for the FAKESTORE API. */
    protected final static String FAKESTOREAPI_BASEURL = ConfigManager.get("baseUrl_fakeStoreAPI");
    /** Endpoint for the FAKESTORE API products resource. */
    protected final static String FAKESTOREAPI_PRODUCTS_ENDPOINT = ConfigManager.get("endPoint_fakeStoreAPI_products");

    // BaseUrl and endpoints for AMADEUS API
    /** Base URL for the AMADEUS API. */
    protected final static String AMADEUSAPI_BASEURL = ConfigManager.get("baseUrl_amadeusAPI");
    /** Endpoint for the AMADEUS API. */
    protected final static String AMADEUSAPI_ENDPOINT = ConfigManager.get("endPoint_amadeusAPI");
    /** Endpoint for the AMADEUS API flight Destinations */
    protected final static String AMADEUSAPI_FLIGHTDESTINATIONS_ENDPOINT = ConfigManager.get("endPoint_flightDestination");


    /**
     * Sets up the test environment by initializing the RestClient instance.
     * This method is executed before any test methods in the class.
     */
    @BeforeTest
    public void setUp() {
        restClient = new RestClient();
    }

}