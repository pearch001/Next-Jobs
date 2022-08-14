package com.NextJobs.NextJobsapi.model.dtos;

import lombok.Data;

@Data
public class userDto {

    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;

    public userDto(String firstName, String lastName, String email, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
    }
}
