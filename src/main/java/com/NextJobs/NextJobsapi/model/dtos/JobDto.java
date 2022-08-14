package com.NextJobs.NextJobsapi.model.dtos;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class JobDto {
    private String description;
    private String title;
    private String company;
    private long salaryUpperBound;
    private long salaryLowerBound;
    private String state;
    @CreationTimestamp
    private Date dateCreated;
    private String applicationDeadline;

    public JobDto(String description, String title, String company, long salaryUpperBound, long salaryLowerBound, String state, Date dateCreated, String applicationDeadline) {
        this.description = description;
        this.title = title;
        this.company = company;
        this.salaryUpperBound = salaryUpperBound;
        this.salaryLowerBound = salaryLowerBound;
        this.state = state;
        this.dateCreated = dateCreated;
        this.applicationDeadline = applicationDeadline;
    }
}
