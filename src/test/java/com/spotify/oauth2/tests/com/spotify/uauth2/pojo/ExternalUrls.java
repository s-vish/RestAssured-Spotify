
package com.spotify.oauth2.tests.com.spotify.uauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalUrls {

    @JsonProperty("spotify")
    private String spotify;
}
