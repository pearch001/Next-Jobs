package com.NextJobs.NextJobsapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Individual")
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String careerAccomplishment;

    private int yearsOfExperience;

    private String Location;

    private String websiteUrl;

    private String cvUrl;

    private String faceBookUrl;

    private String twitterUrl;

    private String linkedInUrl;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "skills_ID")
    private Skills skills;

    @ManyToOne
    @JoinColumn(name = "tools_ID")
    private Tools tools;





}
