package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCreationTest {

    @Test(description = "Verify if Login API is working...")
    public void createAccountTest(){

        SignUpRequest signUpRequest = new SignUpRequest.Builder()
                .userName("BernardoSJ21")
                .email("bernardo.salinas.jaquez.qa21@gmail.com")
                .firstName("Bernardo")
                .lastName("Salinas")
                .password("Bernardo123")
                .mobileNumber("1234567890")
                .build();


        AuthService authService = new AuthService();
        Response response = authService.signUp(signUpRequest);
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
        Assert.assertEquals(response.statusCode(), 200);
    }

}
