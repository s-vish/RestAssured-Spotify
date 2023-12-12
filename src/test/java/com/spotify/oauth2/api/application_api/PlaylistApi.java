package com.spotify.oauth2.api.application_api;

import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.playlist.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class PlaylistApi {
 //   static String access_token = TokenManager.getToken();

    @Step
    public static Response post(Playlist requestPlaylist) {
        return given(getRequestSpec())
                .auth().oauth2(getToken())
                .body(requestPlaylist)
                .when().post(USERS+"/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS)
                .then().log().all().extract().response();
    }

    @Step
    public static Response post(Playlist requestPlaylist, String access_token) {
        return given(getRequestSpec())
                .auth().oauth2(access_token)
                .body(requestPlaylist)
                .when().post(USERS+"/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS)
                .then().log().all().extract().response();

    }

    @Step
    public static Response get(String playlistId) {
        return given(getRequestSpec())
                .auth().oauth2(getToken())
                .when().get(PLAYLISTS+"/" + playlistId)
                .then().log().all().extract().response();
    }

    @Step
    public static Response put(Playlist requestPlaylist, String playlistId) {
        return given(getRequestSpec())
                .auth().oauth2(getToken())
                .body(requestPlaylist)
                .when().put(PLAYLISTS+"/" + playlistId)
                .then().log().all().extract().response();

    }
}
