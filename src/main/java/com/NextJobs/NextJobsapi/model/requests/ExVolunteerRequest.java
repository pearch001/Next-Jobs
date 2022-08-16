package com.NextJobs.NextJobsapi.model.requests;

import com.NextJobs.NextJobsapi.model.entities.Skills;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
public class ExVolunteerRequest {

    private String currentPosition;

    private String education;

    private String country;

    private String location;

    private String phoneNumber;

    private String aboutYourself;

    private String dob;

    private String websiteUrl;

    private String cvUrl;

    private String picUrl;

    private String githubUrl;

    private String linkedInUrl;

    private String skills;
}
