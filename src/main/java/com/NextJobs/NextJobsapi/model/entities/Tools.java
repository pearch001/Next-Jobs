package com.NextJobs.NextJobsapi.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "tools")
public class Tools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}