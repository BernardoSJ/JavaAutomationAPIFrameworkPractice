package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITest {

    @Test(description = "Verify if Login API is working...")
    public void loginTest(){
        RestAssured.baseURI= "http://64.227.160.186:8080";
        RequestSpecification requestLogin = RestAssured.given();
        RequestSpecification header = requestLogin.header("Content-Type", "application/json");
        RequestSpecification body = header.body("{\"username\": \"BernardoSJ\",\"password\": \"Bernardo$1998\"}");
        Response response = body.post("/api/auth/login");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
