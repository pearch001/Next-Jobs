package com.NextJobs.NextJobsapi.model.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FacebookLoginRequest {
    @NotBlank
    private String accessToken;
}
