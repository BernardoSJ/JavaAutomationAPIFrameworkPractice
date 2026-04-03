package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest {

    @Test(description = "Verify if Forgot Password API is working...")
    public void forgotPasswordTest(){
        AuthService authService = new AuthService();

        Response response = authService.forgotPassword("bernardo.salinas.jaquez.qa@gmail.com");
        Assert.assertEquals(response.statusCode(), 200);
    }

}
