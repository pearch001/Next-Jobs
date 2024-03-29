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
@Entity(name="Individual")
public class Individual {
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

    private String dob;

    private String websiteUrl;

    private String cvUrl;

    private String githubUrl;

    private String linkedInUrl;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;


    private String skills;

    public Individual(String currentPosition, String education, String country, String location, String phoneNumber, String aboutYourself, String dob, String websiteUrl, String cvUrl,  String githubUrl, String linkedInUrl, AppUser appUser, String skills) {
        this.currentPosition = currentPosition;
        this.education = education;
        this.country = country;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.aboutYourself = aboutYourself;
        this.dob = dob;
        this.websiteUrl = websiteUrl;
        this.cvUrl = cvUrl;

        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
        this.appUser = appUser;
        this.skills = skills;
    }
}
