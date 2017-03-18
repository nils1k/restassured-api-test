package com.vimond.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class Config {

    protected static String baseURI;

    @BeforeClass
    public static void mainSetup(){
        baseURI = "https://httpbin.org";
    }

    @AfterClass
    public static void cleanUp() {
    }
}
