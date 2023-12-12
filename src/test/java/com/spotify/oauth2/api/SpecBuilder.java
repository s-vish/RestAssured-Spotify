package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASE_PATH;
import static io.restassured.RestAssured.config;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec() {
        return  new RequestSpecBuilder().setBaseUri(ConfigLoader.getInstance().getBaseUri())
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }



}
