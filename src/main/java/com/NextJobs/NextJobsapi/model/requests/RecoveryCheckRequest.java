package com.NextJobs.NextJobsapi.model.requests;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RecoveryCheckRequest {
    @NotNull
    private String text;

    @NotNull
    private String userame;
}
