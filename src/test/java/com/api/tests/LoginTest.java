package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class LoginTest {

    @Test(description = "Verify if Login API is working...")
    public void loginTest(){
        LoginRequest loginRequest = new LoginRequest("BernardoSJ", "Bernardo$1998");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);

        Assert.assertTrue(loginResponse.getToken() != null);
        Assert.assertEquals(loginResponse.getEmail(), "bernardo.salinas.jaquez.qa@gmail.com");
        Assert.assertEquals(loginResponse.getId(), 4841);
    }

}
