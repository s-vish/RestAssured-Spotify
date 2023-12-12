package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.application_api.GenreApi;
import com.spotify.oauth2.api.application_api.PlaylistApi;
import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.Playlist;
import com.spotify.oauth2.tests.com.spotify.uauth2.pojo.genre_seeds.AvailableGenreSeeds;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakersUtils.generateDescription;
import static com.spotify.oauth2.utils.FakersUtils.generateName;
import static org.testng.Assert.assertEquals;

public class AvailableGenreTest {

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Test(description = "User should able to fetch available genre")
    public void shouldAbleToUpdatePlayList() {

        Response response = GenreApi.get();
        assertStatusCode(response.statusCode(), 200);


    }
}
