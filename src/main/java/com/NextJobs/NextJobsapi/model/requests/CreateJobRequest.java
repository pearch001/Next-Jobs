package com.NextJobs.NextJobsapi.model.requests;

import lombok.Data;

@Data
public class CreateJobRequest {
    private String description;
    private String title;
    private String type;
    private String location;
    private String experience;
    private String skills;
    private String companyName;
    private String companyEmailAddress;
    private String website;
    private String applicationDeadline;
}
