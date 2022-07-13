
package com.NextJobs.NextJobsapi.model.requests;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "access_token",
    "expires_in"
})
@Generated("jsonschema2pojo")
public class LinkedInLoginRequest {

    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("expires_in")
    public Integer expiresIn;

}
