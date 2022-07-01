package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LinkedInService {
    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired private JwtTokenUtil tokenProvider;

    @Autowired
    private AppUserDao appUserDao;
}
