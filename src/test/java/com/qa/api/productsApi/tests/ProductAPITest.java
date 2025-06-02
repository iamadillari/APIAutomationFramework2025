package com.qa.api.productsApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ProductAPITest class contains test cases for interacting with the Product API.
 * It extends the BaseTest class to utilize common test setup and utilities.
 */
public class ProductAPITest extends BaseTest {

    /**
     * Test to retrieve all products from the Product API.
     * Steps:
     * 1. Send a GET request to fetch all products.
     * 2. Validate the response status code.
     * 3. Deserialize the response body into an array of Product objects.
     * 4. Print the details of each product to the console.
     */
    @Test
    public void getAllProductsTest() {
        // Send a GET request to fetch all products
        Response allProductsResponse = restClient.get(FAKESTOREAPI_BASEURL, FAKESTOREAPI_PRODUCTS_ENDPOINT,
                null, null, AuthType.NO_AUTH, ContentType.ANY);

        // Validate the response status code is 200 (OK)
        Assert.assertEquals(allProductsResponse.statusCode(), 200, "Validating status code is 200");

        // Deserialize the response body into an array of Product objects
        Product[] products = JsonUtils.deserialize(allProductsResponse, Product[].class);

        // Iterate through the products and print their details to the console
        for(Product p : products) {
            System.out.println("<<<<<<<<<<Product details are>>>>>>>>>>");
            System.out.println("Product id: " + p.getId());
            System.out.println("Product title: " + p.getTitle());
            System.out.println("Product price: " + p.getPrice());
            System.out.println("Product description: " + p.getDescription());
            System.out.println("Product category: " + p.getCategory());
            System.out.println("Product image link: " + p.getImage());
            System.out.println("Product rate is: " + p.getRating().getRate());
            System.out.println("Product count is: " + p.getRating().getCount());
            System.out.println("========================================");
        }
    }
}