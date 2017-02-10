package com.vimond.api;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PUTOperationIT extends Config {

    static String testURL;

    @Before
    public void additionalConfig() {
        // Bygger URL til test-api basert på baseURI i Config baseklasse
        testURL = baseURI + "/put";
    }

    @Test // Legger inn en PUT kommando, med deler opp i response + sjekker
    public void myFourthRestAssuredTest() {

        // Dytter data inn i en MAP
        Map<String, String> car = new HashMap<>();

        // Variabel som senere sendes til funksjon
        car.put("plateNumber", "xyx1111");
        car.put("brand", "audi");
        car.put("colour", "blå");

        SimplePUT addtoBase = new SimplePUT();

        Response response = addtoBase.putDataInAPI(car, testURL);
        response.then().statusCode(200);

        System.out.println(response.asString());
    }
}
