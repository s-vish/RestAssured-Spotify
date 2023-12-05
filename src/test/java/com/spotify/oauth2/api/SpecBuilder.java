package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static com.spotify.oauth2.api.Route.BASE_PATH;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.requestSpecification;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ConfigLoader.getInstance().getBaseUri())
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .log(LogDetail.ALL);
        return requestSpecification = requestSpecBuilder.build();
    }
}
