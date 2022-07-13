
package com.NextJobs.NextJobsapi.model.entities.linkedin;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "localized",
    "preferredLocale"
})
@Generated("jsonschema2pojo")
public class LastName {

    @JsonProperty("localized")
    public Localized__1 localized;
    @JsonProperty("preferredLocale")
    public PreferredLocale__1 preferredLocale;

}
