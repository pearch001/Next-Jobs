package com.NextJobs.NextJobsapi.model.entities.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUser {


    // These six fields are included in all Google ID Tokens.
    private String email;
    private String name;
    private String picture;
    private String firstName;
    private String lastName;
    private String locale;


}
