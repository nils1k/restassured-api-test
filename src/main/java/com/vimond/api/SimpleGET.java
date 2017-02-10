package com.vimond.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Created by kristian on 10/02/17.
 */
public class SimpleGET {

    public Response getResponsAPI(String URL) {
        Response response = given().when().get(URL);
        return response;
    }
}
