
package com.NextJobs.NextJobsapi.model.entities.linkedin;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "handle",
    "handles~"
})
@Generated("jsonschema2pojo")
public class LinkedInUserEmail {

    @JsonProperty("handle")
    public String handle;
    @JsonProperty("handles~")
    public Handles handles;

}
