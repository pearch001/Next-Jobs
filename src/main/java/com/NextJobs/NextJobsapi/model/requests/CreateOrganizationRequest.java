package com.NextJobs.NextJobsapi.model.requests;

import lombok.Data;

@Data
public class CreateOrganizationRequest {

    private String name;

    private String email;

    private String phone;

    private String country;

    private String location;

    private String website;

    private String description;
}
