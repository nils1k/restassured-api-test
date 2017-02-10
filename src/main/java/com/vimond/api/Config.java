package com.vimond.api;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import io.restassured.RestAssured;

public class Config {

    protected static String baseURI;

    @BeforeClass
    public static void mainSetup(){
        //Se https://resttesttest.com/
        baseURI = "https://httpbin.org";
    }

    @AfterClass
    public static void cleanUp() {
    }
}
