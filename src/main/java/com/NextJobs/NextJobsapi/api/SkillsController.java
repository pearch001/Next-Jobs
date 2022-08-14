package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.model.requests.ExVolunteerRequest;
import com.NextJobs.NextJobsapi.services.ProfilesService;
import com.NextJobs.NextJobsapi.services.SkillsService;
import com.NextJobs.NextJobsapi.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequestMapping("/api/v1/skills")
@RestController
@CrossOrigin("*")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse> addExVolunteer(@RequestBody ExVolunteerRequest exVolunteerRequest){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/profile/exVolunteer/add").toUriString());
        log.info("In the controller");
        //profilesService.saveExVolunteer(exVolunteerRequest);
        return new ResponseEntity<>(new ApiResponse(true,"Profile created"), HttpStatus.ACCEPTED);
    }
}
