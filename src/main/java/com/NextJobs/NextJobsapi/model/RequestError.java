package com.NextJobs.NextJobsapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RequestError {
    private String message;
    private String fieldName;
}
