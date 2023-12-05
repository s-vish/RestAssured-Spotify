
package com.spotify.oauth2.tests.com.spotify.uauth2.pojo.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error__1 {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
}
