
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
    "displayImage"
})
@Generated("jsonschema2pojo")
public class ProfilePicture {

    @JsonProperty("displayImage")
    public String displayImage;

}
