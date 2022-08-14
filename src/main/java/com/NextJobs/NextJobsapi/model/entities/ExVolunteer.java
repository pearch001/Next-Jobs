package com.NextJobs.NextJobsapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ExVolunteer")
public class ExVolunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currentPosition;

    private String education;

    private String country;

    private String location;

    private String phoneNumber;

    @Lob
    private String aboutYourself;

    private Date dob;

    private String websiteUrl;

    private String cvUrl;

    private String faceBookUrl;

    private String githubUrl;

    private String linkedInUrl;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;


    private String skills;

    public ExVolunteer(String currentPosition, String education, String country, String location, String phoneNumber, String aboutYourself, Date dob, String websiteUrl, String cvUrl, String faceBookUrl, String githubUrl, String linkedInUrl, AppUser appUser, String skills) {
        this.currentPosition = currentPosition;
        this.education = education;
        this.country = country;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.aboutYourself = aboutYourself;
        this.dob = dob;
        this.websiteUrl = websiteUrl;
        this.cvUrl = cvUrl;
        this.faceBookUrl = faceBookUrl;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
        this.appUser = appUser;
        this.skills = skills;
    }
}
