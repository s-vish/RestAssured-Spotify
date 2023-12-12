package com.spotify.oauth2.tests;

import static com.spotify.oauth2.api.application_api.UsersApi.getCurrentUserProfile;
import static com.spotify.oauth2.api.application_api.UsersApi.getUserProfile;
import static org.testng.Assert.assertEquals;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersTest {

    @Test
    public void testGetCurrentUser(){
        Response response = getCurrentUserProfile();
        assertEquals(response.statusCode(),200);
    }

    @Test
    public void testGetUserById(){
        Response response = getUserProfile(ConfigLoader.getInstance().getUserId());
        assertEquals(response.statusCode(),200);
    }
}
