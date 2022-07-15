
package com.NextJobs.NextJobsapi.model.entities.linkedin.linkedinuser;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "localizedLastName",
    "profilePicture",
    "firstName",
    "lastName",
    "id",
    "localizedFirstName"
})
@Generated("jsonschema2pojo")
public class LinkedInUser {

    @JsonProperty("localizedLastName")
    public String localizedLastName;
    @JsonProperty("profilePicture")
    public ProfilePicture profilePicture;
    @JsonProperty("firstName")
    public FirstName firstName;
    @JsonProperty("lastName")
    public LastName lastName;
    @JsonProperty("id")
    public String id;
    @JsonProperty("localizedFirstName")
    public String localizedFirstName;

}
