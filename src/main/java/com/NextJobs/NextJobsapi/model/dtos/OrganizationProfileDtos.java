package com.NextJobs.NextJobsapi.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationProfileDtos {
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private String name;

    private String OrganizationEmail;

    private String phone;

    private String country;

    private String location;

    private String website;

    private String description;
}
