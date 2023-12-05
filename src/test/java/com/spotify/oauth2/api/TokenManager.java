package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        try {
            if(access_token==null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing Token!!!");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            }
            else {
                System.out.println("Token is good to use.");
            }
        }
        catch (Exception e){
            throw new RuntimeException("Abort!!! Failed to get token.");
        }
        return access_token;
    }

    private static Response renewToken(){
        Map<String, String> formBody = new HashMap<>();
        formBody.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formBody.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formBody.put("client_id",ConfigLoader.getInstance().getClientId());
        formBody.put("client_secret",ConfigLoader.getInstance().getClientSecret());

        Response response = given()
                .baseUri(ConfigLoader.getInstance().getRenewTokenUri())
                .config(config.encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .contentType(ContentType.URLENC)
                .formParams(formBody)
//                .log().all()
                .when().post(API+TOKEN)
                .then().log().all()
                .extract().response();
        if(response.statusCode()!=200){
            throw new RuntimeException("Abort!!! Renew Token failed.");
        }
        return response;
    }
}
