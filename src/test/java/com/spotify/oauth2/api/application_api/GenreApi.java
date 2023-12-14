package com.spotify.oauth2.api.application_api;

import com.spotify.oauth2.api.TokenManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.*;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class GenreApi {

    @Step
    public static Response get() {
        return given(getRequestSpec())
                .auth().oauth2(getToken())
                .when().get(RECOMMENDATION+AVAILABLE_GENRE_SEEDS)
                .then().spec(getResponseSpec()).extract().response();
    }
}
