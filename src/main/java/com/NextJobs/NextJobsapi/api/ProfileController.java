package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.model.dtos.ProfileDtos;
import com.NextJobs.NextJobsapi.model.entities.ExVolunteer;
import com.NextJobs.NextJobsapi.model.entities.Individual;
import com.NextJobs.NextJobsapi.model.entities.Job;
import com.NextJobs.NextJobsapi.model.requests.ExVolunteerRequest;
import com.NextJobs.NextJobsapi.model.requests.IndividualRequest;
import com.NextJobs.NextJobsapi.services.JobService;
import com.NextJobs.NextJobsapi.services.ProfilesService;
import com.NextJobs.NextJobsapi.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequestMapping("/api/v1/profile")
@RestController
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfilesService profilesService;

    @PostMapping(value = "/individual/add")
    public ResponseEntity<ApiResponse> addIndividual(@RequestBody IndividualRequest individual){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/profile/individual/add").toUriString());
        log.info("In the controller");
        profilesService.saveIndividual(individual);
        return new ResponseEntity<>(new ApiResponse(true,"Profile created"), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/exVolunteer/add")
    public ResponseEntity<ApiResponse> addExVolunteer(@RequestBody ExVolunteerRequest exVolunteerRequest){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/profile/exVolunteer/add").toUriString());
        log.info("In the controller");
        profilesService.saveExVolunteer(exVolunteerRequest);
        return new ResponseEntity<>(new ApiResponse(true,"Profile created"), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getProfile")
    public ProfileDtos getProfile() {return profilesService.loadProfile();}
}
