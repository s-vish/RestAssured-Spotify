package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.application_api.PlaylistApi;
import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.playlist.Playlist;
import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.error.Error;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.utils.FakersUtils.generateDescription;
import static com.spotify.oauth2.utils.FakersUtils.generateName;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class PlayListTests {

    @Step
    public Playlist playlistBuilder(String name, String description, boolean isPublic) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.set_public(isPublic);
        return playlist;
    }

    @Step
    public void assertPlaylistEquals(Playlist requestPlaylist, Playlist responsePlaylist) {
        assertEquals(responsePlaylist.getName(), requestPlaylist.getName());
        assertEquals(responsePlaylist.getDescription(), requestPlaylist.getDescription());
        assertEquals(responsePlaylist.get_public(), requestPlaylist.get_public());
    }

    @Step
    public void assertError(Error responseError, int expectedStatusCode, String expectedMsg) {
        assertEquals(responseError.getError().getStatus(), expectedStatusCode);
        assertEquals(responseError.getError().getMessage(), expectedMsg);

    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Test(description = "Test Create Playlist")
    public void shouldAbleToCreatePlayList() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);

        assertStatusCode(response.statusCode(), 201);
        Playlist playlistResp = response.as(Playlist.class);
        assertPlaylistEquals(requestPlaylist, playlistResp);
    }


    @Test(description = "Test User should fetch the playlist by Playlist ID")
    public void shouldAbleToGetPlayList() {

        Response response = PlaylistApi.get(DataLoader.getInstance().getReadPlaylist());
        assertStatusCode(response.statusCode(), 200);
        Playlist playlist = response.as(Playlist.class);
        assertEquals(playlist.getName(), "Marathii");
        assertEquals(playlist.getDescription(), "Marathi requestPlaylist description");
        assertEquals(playlist.get_public(), false);

    }

    @Test(description = "User should able to update the Playlist")
    public void shouldAbleToUpdatePlayList() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.put(requestPlaylist, DataLoader.getInstance().getUpdatePlaylist());
        assertStatusCode(response.statusCode(), 200);
    }


    @Test(description = "User should not be able to create playlist without Name")
    public void shouldNotBeAbleToCreatePlayListWithoutName() {
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);

        assertStatusCode(response.statusCode(), 400);
        Error error = response.as(Error.class);
        assertError(error, 400, "Missing required field: name");
    }

    @Test(description = "User should not able to create playlist using expired token")
    public void shouldNotBeAbleToCreatePlayListWithExpiredToken() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("");
        requestPlaylist.setDescription("Marathi requestPlaylist description");
        requestPlaylist.set_public(false);

        Response response = PlaylistApi.post(requestPlaylist, "accessToken");
        assertStatusCode(response.statusCode(), 401);

        Error error = response.as(Error.class);
        assertError(error, 401, "Invalid access token");
    }

}
