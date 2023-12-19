package com.spotify.oauth2.tests.com.spotify.uauth2.pojo.users;

import lombok.Data;

import java.util.ArrayList;

@Data
public class User {
    private String display_name;
    private ExternalUrls external_urls;
    private String href;
    private String id;
    private ArrayList<Image> images;
    private String type;
    private String uri;
    private Followers followers;
}
