package com.qa.api.productsApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * Test class for validating the FakeStore API endpoints.
 * Extends the BaseTest class to utilize common setup and configurations.
 * Contains test methods for verifying product details such as image URLs, ratings, and multiple attributes.
 */
public class ProductApiWithJsonPathTest extends BaseTest {

    /**
     * Test method to validate the retrieval of product image URLs.
     * Sends a GET request to the FakeStore API and verifies the response status code.
     * Extracts and validates that the image URLs are not null.
     */
    @Test
    public void getProductImageUrlTest() {
        // Sending GET request to fetch product details
        Response productDetailsResponse = restClient.get(FAKESTOREAPI_BASEURL, FAKESTOREAPI_PRODUCTS_ENDPOINT,
                null, null, AuthType.NO_AUTH, ContentType.JSON);

        // Validating the response status code
        Assert.assertEquals(productDetailsResponse.statusCode(), 200, "Validating Status code is 200");

        // Extracting and printing product image URLs
        System.out.println("<<<<<<<<<<<<Images are>>>>>>>>>>>>");
        List<String> productsImageUrls = JsonPathValidatorUtil.readList(productDetailsResponse, "$.[*].image");
        System.out.println("----Images are----");
        for (String e : productsImageUrls) {
            System.out.println(e);
            // Validating that each image URL is not null
            Assert.assertNotNull(e, "Validating that Image URL is not null");
        }
        System.out.println("<<<<<<<<End of Test case>>>>>>>>");
    }

    /**
     * Test method to validate the retrieval of product ratings.
     * Sends a GET request to the FakeStore API and verifies the response status code.
     * Extracts and prints the rating details (rate and count) for each product.
     */
    @Test
    public void getProductsRatingTest() {
        // Sending GET request to fetch product details
        Response productDetailsResponse = restClient.get(FAKESTOREAPI_BASEURL, FAKESTOREAPI_PRODUCTS_ENDPOINT,
                null, null, AuthType.NO_AUTH, ContentType.JSON);

        // Validating the response status code
        Assert.assertEquals(productDetailsResponse.statusCode(), 200, "Validating Status code is 200");

        // Extracting and printing product ratings
        System.out.println("<<<<<<<<<<<<Ratings are>>>>>>>>>>>>");
        List<Map<String, Object>> ratingDetails = JsonPathValidatorUtil.readListOfMaps(productDetailsResponse, "$.[*].rating");
        for (Map<String, Object> e : ratingDetails) {
            System.out.println(e.get("rate"));
            System.out.println(e.get("count"));
        }
        System.out.println("<<<<<<<<<<<<<<<<<Ratings Detail Ended>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<End of Test case>>>>>>>>");
    }

    /**
     * Test method to validate the retrieval of multiple product attributes.
     * Sends a GET request to the FakeStore API and verifies the response status code.
     * Extracts and prints details such as ID, title, price, image URL, and rating for each product.
     */
    @Test
    public void getProductMultipleAttributesDetailTest() {
        // Sending GET request to fetch product details
        Response productDetailsResponse = restClient.get(FAKESTOREAPI_BASEURL, FAKESTOREAPI_PRODUCTS_ENDPOINT,
                null, null, AuthType.NO_AUTH, ContentType.JSON);

        // Validating the response status code
        Assert.assertEquals(productDetailsResponse.statusCode(), 200, "Validating Status code is 200");

        // Extracting and printing multiple product attributes
        System.out.println("<<<<<<<<<<<<Product Details are>>>>>>>>>>>>");
        List<Map<String, Object>> idTitleRatingDetails = JsonPathValidatorUtil.readListOfMaps(productDetailsResponse, "$.[*].['id','title','price','image','rating']");
        for (Map<String, Object> e : idTitleRatingDetails) {
            System.out.println("Product ID-> " + e.get("id"));
            System.out.println("Product Title-> " + e.get("title"));
            System.out.println("Product Price-> " + e.get("price"));
            System.out.println("Product Image Link-> " + e.get("image"));
            Map<String, Object> rating = (Map<String, Object>) e.get("rating");
            System.out.println("Rating's Rate-> " + rating.get("rate"));
            System.out.println("Rating's Count-> " + rating.get("count"));
            System.out.println("------------------------------------");
        }
        System.out.println("<<<<<<<<<<<<<<<<<ID-Title-Ratings Detail Ended>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<End of Test case>>>>>>>>");
    }


    /**
     * Test method to validate the retrieval of the minimum price of products.
     * Sends a GET request to the FakeStore API and verifies the response status code.
     * Extracts the minimum price from all the products and prints it.
     */
    @Test
    public void getMinimumPriceProductTest() {
        // Sending GET request to fetch product details
        Response productDetailsResponse = restClient.get(FAKESTOREAPI_BASEURL, FAKESTOREAPI_PRODUCTS_ENDPOINT,
                null, null, AuthType.NO_AUTH, ContentType.JSON);

        // Validating the response status code is 200
        Assert.assertEquals(productDetailsResponse.statusCode(), 200, "Validating Status code is 200");

        // Extract the minimum price from all the Products
        Double minPrice = JsonPathValidatorUtil.read(productDetailsResponse, "min($.[*].price)");
        System.out.println("Minimum Price of Product from all the products-> " + minPrice);
        System.out.println("<<<<<<<<End of Test case>>>>>>>>");
    }

}