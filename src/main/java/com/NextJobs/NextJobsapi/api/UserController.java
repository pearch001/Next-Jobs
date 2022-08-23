package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.model.dtos.ProfileDtos;
import com.NextJobs.NextJobsapi.model.dtos.userDto;
import com.NextJobs.NextJobsapi.services.AppUserServiceImpl;
import com.NextJobs.NextJobsapi.services.ProfilesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/nextjobs/v1/user")
@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private ProfilesService profilesService;

    @GetMapping(value = "/get")
    public userDto getUser(){ return appUserService.loadUser();}


}
