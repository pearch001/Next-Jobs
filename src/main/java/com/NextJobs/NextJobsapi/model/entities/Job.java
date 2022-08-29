package com.NextJobs.NextJobsapi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String title;
    private String company;
    private String location;
    @CreationTimestamp
    private Date dateCreated;
    private String experience;
    private String skills;
    private String companyName;
    private String emailAddress;
    private String website;
    private String companyDescription;

    public Job(String description, String title, String company, String location, String experience, String skills, String companyName, String emailAddress, String website, String companyDescription) {
        this.description = description;
        this.title = title;
        this.company = company;
        this.location = location;
        this.experience = experience;
        this.skills = skills;
        this.companyName = companyName;
        this.emailAddress = emailAddress;
        this.website = website;
        this.companyDescription = companyDescription;
    }
}
