package com.vimond.api;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GETOperationIT extends Config {

    static String testURL;

    @Before
    public void additionalSetup() {
        testURL = baseURI + "/get"; // bygger URL til test-api basert på baseURI i GenericConfigAPI
    }

    @Test // Sjekker at body i en GET inneholder spesifikk tekst
    public void mySecondModelTest() {
        SimpleGET getThis = new SimpleGET();
        Response response = getThis.getResponsAPI(testURL);

        response.then().body(containsString("httpbin.org"));
    }

    @Test // Sjekker at body i en GET inneholder spesifikk tekst
    public void myThirdModelTest() {
        given().when().get(testURL).then().body(containsString("google"));
    }
}
