
package com.NextJobs.NextJobsapi.model.entities.linkedin;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "firstName",
    "lastName",
    "profilePicture"
})
@Generated("jsonschema2pojo")
public class LinkedInUser {

    @JsonProperty("id")
    public String id;
    @JsonProperty("firstName")
    public FirstName firstName;
    @JsonProperty("lastName")
    public LastName lastName;
    @JsonProperty("profilePicture")
    public ProfilePicture profilePicture;

}
