package com.NextJobs.NextJobsapi.model.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}

