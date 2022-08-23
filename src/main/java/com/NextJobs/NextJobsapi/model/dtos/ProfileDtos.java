package com.NextJobs.NextJobsapi.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
public class ProfileDtos {

    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;

    private String currentPosition;

    private String education;

    private String country;

    private String location;

    private String phoneNumber;


    private String aboutYourself;

    private String dob;

    private String websiteUrl;

    private String cvUrl;

    private String githubUrl;

    private String linkedInUrl;
}
