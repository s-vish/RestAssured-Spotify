package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.application_api.GenreApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AvailableGenreTest {

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Test(description = "User should able to fetch available genre")
    public void shouldAbleToFetchAvailableGenre() {

        Response response = GenreApi.get();
        assertStatusCode(response.statusCode(), 200);
    }
}
