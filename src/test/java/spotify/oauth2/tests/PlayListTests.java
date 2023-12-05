package spotify.oauth2.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayListTests {

    String access_token = "BQC4z_NlvJy_xWpfM0hnhUVzoHNsEHbX1f_5QXZHPTCVsQ6BCW141NVJAIC-yw0gBdTh6BOAXg8ly0Gz7DXCAYTw1_5dAF2g79B-4bIusmQ4JihQVDl5wROzWvZ7oU7f_LhlQcbwK_HRa_ELZ1gcG06XV7ByOseuVFyfeQLBYlokcALh7QL_2uJWDYL6DP-emlSVMqEDuvaZ9arJsyGscUIYRMD87yFjrnNFaXZ5BYh7JjrnTXj-rI4BMdMSVkkHaV9IRm2TLdhldQY_";
    String userId = "313it5nykzxn2umgdzucatrht6kq";
    Map<String, Object> bodyData = new HashMap<>();

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

    }

    @Test // Create Playlist using RequestSpecBuilder
    public void shouldAbleToCreatePlayList() {
        String payload = "{\n" +
                "    \"name\": \"Hind12\",\n" +
                "    \"description\": \"Hindi All songsf\",\n" +
                "    \"public\": false\n" +
                "}";

        Response response = given(requestSpecification)
                .auth().oauth2(access_token)
                .body(payload)
                .when().post("/users/313it5nykzxn2umgdzucatrht6kq/playlists")
                .then().log().all().extract().response();
        assertEquals(response.statusCode(), 201);
        assertEquals(response.path("name"), "Hind12");
    }


    //create playlist without using RequestSpecBuilder
    @Test
    public void createPlayListTest() {
        bodyData.put("name", "Hindii");
        bodyData.put("description", "Test descriptionb");
        bodyData.put("public", false);
        Response response = given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .auth().oauth2(access_token)
                .header("Accept", "application/json")
                .body(bodyData)
                .log().all()
                .when().post("/users/" + userId + "/playlists")
                .then()
                .log().all()
                .extract().response();

        assertEquals(response.statusCode(), 201);
        assertEquals(response.path("name"), "Hindii");

    }

    @Test // Get Playlist using RequestSpecBuilder
    public void shouldAbleToGetPlayList() {

        Response response = given(requestSpecification)
                .auth().oauth2(access_token)
                .when().get("/playlists/1LBZwW7loocXFCpK8p35DR")
                .then().log().all().extract().response();
        assertEquals(response.statusCode(), 200);
        assertEquals(response.path("items[0].name"), "Hind12");
    }

    @Test // Update Playlist using RequestSpecBuilder
    public void shouldAbleToUpdatePlayList() {
        String payload = "{\n" +
                "    \"name\": \"Hindi_mantraa\",\n" +
                "    \"description\": \"Hindi All songsf\",\n" +
                "    \"public\": false\n" +
                "}";

        Response response = given(requestSpecification)
                .auth().oauth2(access_token)
                .body(payload)
                .when().put("/playlists/1LBZwW7loocXFCpK8p35DR")
                .then().log().body().extract().response();
        assertEquals(response.statusCode(), 200);
    }

    @Test // Create Playlist using RequestSpecBuilder
    public void shouldNotBeAbleToCreatePlayListWithoutName() {
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"Hindi All songsf\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification)
                .auth().oauth2(access_token)
                .body(payload)
                .when().post("/users/313it5nykzxn2umgdzucatrht6kq/playlists")
                .then().log().all()
                .assertThat().statusCode(400)
                .body("error.status", equalTo(400),
        "error.message",equalTo("Missing required field: name"));
    }

    @Test // Create Playlist using RequestSpecBuilder
    public void shouldNotBeAbleToCreatePlayListWithExpiredToken() {
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"Hindi All songsf\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification)
                .auth().oauth2("access_token_dskjafdjsfkjhd")
                .body(payload)
                .when().post("/users/313it5nykzxn2umgdzucatrht6kq/playlists")
                .then().log().all()
                .assertThat().statusCode(401)
                .body("error.status", equalTo(401),
                        "error.message",equalTo("Invalid access token"));
    }

}
