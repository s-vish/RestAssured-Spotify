package com.spotify.oauth2.api.application_api;

import com.spotify.oauth2.api.TokenManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.*;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class UsersApi {
//    static String access_token = TokenManager.getToken();
    @Step
    public static Response getCurrentUserProfile() {
        return given(getRequestSpec())
                .auth().oauth2(getToken())
                .when().get(ME)
                .then().spec(getResponseSpec()).extract().response();
    }

    @Step
    public static Response getUserProfile(String userId) {
        return given(getRequestSpec())
                .auth().oauth2(getToken())
                .when().get(USERS+"/"+userId)
                .then().spec(getResponseSpec()).extract().response();
    }
}
