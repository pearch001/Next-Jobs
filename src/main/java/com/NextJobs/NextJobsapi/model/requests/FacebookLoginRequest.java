package com.NextJobs.NextJobsapi.model.requests;

import javax.validation.constraints.NotBlank;

public class FacebookLoginRequest {
    @NotBlank
    private String accessToken;
}
