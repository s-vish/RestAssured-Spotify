package com.spotify.oauth2.tests;

import static com.spotify.oauth2.api.application_api.UserApi.getCurrentUserProfile;
import static com.spotify.oauth2.api.application_api.UserApi.getUserProfileById;
import static org.testng.Assert.assertEquals;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserTest {

    @Test
    public void testGetCurrentUser(){
        Response response = getCurrentUserProfile();
        assertEquals(response.statusCode(),200);
    }

    @Test
    public void testGetUserById(){
        Response response = getUserProfileById(ConfigLoader.getInstance().getUserId());
        assertEquals(response.statusCode(),200);
    }
}
