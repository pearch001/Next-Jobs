package com.NextJobs.NextJobsapi.api;

import com.NextJobs.NextJobsapi.model.dtos.userDto;
import com.NextJobs.NextJobsapi.services.AppUserServiceImpl;
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

    @GetMapping(value = "/get")
    public userDto countJobs(){ return appUserService.loadUser();}
}
