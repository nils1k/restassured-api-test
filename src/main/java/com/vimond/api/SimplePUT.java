package com.vimond.api;

import java.util.Map;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SimplePUT {

    public Response putDataInAPI(Map<String, String> map, String URL) {
        Response response = given().contentType("application/json").body(map).when().put(URL);
        return response;
    }


    public Response putDataInAPI2(Car cars, String URL) {
        Response response = given().contentType("application/json").body(cars).when().put(URL);
        return response;
    }

    public static class Car {

        private String plateNumber;
        private String color;
        private String brand;

        public String setPlateNumber(String string) {
            return string;
        }

        public String setColor(String string) {
            return string;
        }

        public String setBrand(String string) {
            return string;
        }

    }
}
