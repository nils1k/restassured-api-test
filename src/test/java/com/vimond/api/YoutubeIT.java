package com.vimond.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by kristian on 05/03/17.
 * Test av følgende endepunkt i Youtube sitt API:
 * https://content.googleapis.com/youtube/v3/search?part=snippet&q=football&key=<API-nøkkel>
 */
public class YoutubeIT {

    private static final String KEY = System.getenv("YoutubeAPI");
    private static Response response;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://content.googleapis.com";
        RestAssured.basePath = "/youtube/v3";
    }

    @Test
    public void search() {
        response =
            given()
                .queryParam("part", "snippet")
                .queryParam("q", "football")
                .queryParam("key", KEY)
            .when()
                .get("/search")
            .then()
                .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();

        System.out.println(response.asString());
        System.out.println("HELLO!");
    }
}
