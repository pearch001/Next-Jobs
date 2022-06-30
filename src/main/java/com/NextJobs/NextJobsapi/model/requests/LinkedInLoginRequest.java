package com.NextJobs.NextJobsapi.model.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LinkedInLoginRequest {
    @NotBlank
    private String accessToken;
}
