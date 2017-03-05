package com.vimond.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SimpleGET {

    public Response getResponsAPI(String URL) {
        Response response = given().when().get(URL);
        return response;
    }
}
