package com.vimond.api;

import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class SimplePUT {

    public Response putDataInAPI(Map<String, String> map, String URL) {
        Response response =
                given().
                contentType("application/json").
                body(map).
                when().
                put(URL);

        return response;
    }

    public Response putDataInAPI2(Car cars, String URL) {
        Response response =
                given().
                contentType("application/json").
                body(cars).
                when().
                put(URL);

        return response;
    }

    public class Car {
        private String plateNumber;
        private String color;
        private String brand;

        public String getBrand() { return brand; }

        public String getColor() { return color; }

        public String getPlateNumber() {
            return plateNumber;
        }


        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
    }
}
