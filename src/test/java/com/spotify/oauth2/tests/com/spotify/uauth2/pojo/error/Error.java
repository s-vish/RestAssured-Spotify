
package com.spotify.oauth2.tests.com.spotify.uauth2.pojo.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    @JsonProperty("error")
    private Error__1 error;

    @JsonProperty("error")
    public Error__1 getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Error__1 error) {
        this.error = error;
    }

}
