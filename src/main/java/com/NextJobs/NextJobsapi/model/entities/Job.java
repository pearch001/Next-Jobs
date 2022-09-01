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

    private String type;

    private String location;
    @CreationTimestamp
    private Date dateCreated;
    private String experience;
    private String skills;
    private String applicationDeadline;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "organization_id"
    )
    private Organisation organization;

    public Job(String description, String title, String type, String location, String experience, String skills, String applicationDeadline, Organisation organisation) {
        this.description = description;
        this.title = title;
        this.type = type;
        this.location = location;
        this.experience = experience;
        this.skills = skills;
        this.applicationDeadline = applicationDeadline;
        this.organization = organisation;
    }
}
