package com.NextJobs.NextJobsapi.model.requests;

import lombok.*;

@Data
public class SignUpRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
