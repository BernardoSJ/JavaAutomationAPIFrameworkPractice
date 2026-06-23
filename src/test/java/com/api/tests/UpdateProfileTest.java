package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)
public class UpdateProfileTest {

    @Test(description = "Verify if Get Profile API is working...")
    public void updateProfileTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("BernardoSJ","Bernardo$1998"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());

        System.out.println("----------------------------------------------------------------------------------------");

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response = userProfileManagementService.getProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(), "BernardoSJ");

        System.out.println("----------------------------------------------------------------------------------------");

        ProfileRequest profileRequest = new ProfileRequest.Builder()
                .firstName("Berni")
                .lastName("Salinas")
                .email("bernardo.salinas.jaquez.qa@gmail.com")
                .mobileNumber("1234567890")
                .build();

        response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
        System.out.println(response.asPrettyString());

    }

}
