package com.qa.api.productsApi.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAPITest extends BaseTest {

    @Test
    public void getAllProductsTest() {
        Response allProductsResponse = restClient.get(FAKESTOREAPI_BASEURL, FAKESTOREAPI_PRODUCTS_ENDPOINT,
                null, null, AuthType.NO_AUTH, ContentType.ANY);
        Assert.assertEquals(allProductsResponse.statusCode(), 200, "Validating status code is 200");
        Product[] products = JsonUtils.deserialize(allProductsResponse, Product[].class);
        for(Product p : products)
        {
            System.out.println("<<<<<<<<<<Product details are>>>>>>>>>>");
            System.out.println("Product id: "+ p.getId());
            System.out.println("Product title: "+ p.getTitle());
            System.out.println("Product price: "+ p.getPrice());
            System.out.println("Product description: "+ p.getDescription());
            System.out.println("Product category: "+ p.getCategory());
            System.out.println("Product image link: "+ p.getImage());
            System.out.println("Product rate is: "+p.getRating().getRate());
            System.out.println("Product count is: "+p.getRating().getCount());
            System.out.println("========================================");
        }
    }

}