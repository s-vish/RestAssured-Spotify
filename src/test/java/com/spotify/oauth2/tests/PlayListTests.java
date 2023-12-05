package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.application_api.PlaylistApi;
import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.Playlist;
import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.error.Error;
import com.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
public class PlayListTests {
    public Playlist playlistBuilder(String name, String description, boolean isPublic){
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(description);
        playlist.setPublic(isPublic);
        return playlist;
    }

    public void assertPlaylistEquals(Playlist requestPlaylist, Playlist responsePlaylist){
        assertEquals(responsePlaylist.getName(),requestPlaylist.getName());
        assertEquals(responsePlaylist.getDescription(),requestPlaylist.getDescription());
        assertEquals(responsePlaylist.getPublic(),requestPlaylist.getPublic());
    }

    public void assertError(Error responseError,int expectedStatusCode,String expectedMsg){
        assertEquals(responseError.getError().getStatus(),expectedStatusCode);
        assertEquals(responseError.getError().getMessage(),expectedMsg);

    }

    public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
        assertEquals(actualStatusCode,expectedStatusCode);
    }

    @Test // Create Playlist using RequestSpecBuilder
    public void shouldAbleToCreatePlayList() {
        Playlist requestPlaylist = playlistBuilder("English","Playlist description",false);
        Response response = PlaylistApi.post(requestPlaylist);

        assertStatusCode(response.statusCode(), 201);
        Playlist playlistResp = response.as(Playlist.class);
        assertPlaylistEquals(requestPlaylist,playlistResp);
    }


    //create playlist without using RequestSpecBuilder
    @Test
    public void createPlayListTest() {
        Playlist requestPlaylist = playlistBuilder("Marathi","Marathi requestPlaylist description",false);
        Response response = PlaylistApi.post(requestPlaylist);

        assertStatusCode(response.statusCode(), 201);
        Playlist playlistResp = response.as(Playlist.class);
        assertPlaylistEquals(requestPlaylist,playlistResp);

    }

    @Test // Get Playlist using RequestSpecBuilder
    public void shouldAbleToGetPlayList() {

        Response response = PlaylistApi.get(DataLoader.getInstance().getReadPlaylist());

        assertStatusCode(response.statusCode(), 200);
        Playlist playlist = response.as(Playlist.class);
        assertEquals(playlist.getName(),"Marathii");
        assertEquals(playlist.getDescription(),"Marathi requestPlaylist description");
        assertEquals(playlist.getPublic(),false);

    }

    @Test // Update Playlist using RequestSpecBuilder
    public void shouldAbleToUpdatePlayList() {
        Playlist requestPlaylist = playlistBuilder("EngList","Marathi requestPlaylist description",false);
        Response response = PlaylistApi.put(requestPlaylist,DataLoader.getInstance().getUpdatePlaylist());
        assertStatusCode(response.statusCode(), 200);
    }

    @Test // Create Playlist using RequestSpecBuilder
    public void shouldNotBeAbleToCreatePlayListWithoutName() {
        Playlist requestPlaylist = playlistBuilder("","Marathi requestPlaylist description",false);
        Response response = PlaylistApi.post(requestPlaylist);

        assertStatusCode(response.statusCode(), 400);
        Error error = response.as(Error.class);
        assertError(error,400,"Missing required field: name");
    }

    @Test // Create Playlist using RequestSpecBuilder
    public void shouldNotBeAbleToCreatePlayListWithExpiredToken() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("");
        requestPlaylist.setDescription("Marathi requestPlaylist description");
        requestPlaylist.setPublic(false);

        Response response = PlaylistApi.post(requestPlaylist,"accessToken");
        assertStatusCode(response.statusCode(), 401);

        Error error = response.as(Error.class);
        assertError(error,401,"Invalid access token");
    }

}
