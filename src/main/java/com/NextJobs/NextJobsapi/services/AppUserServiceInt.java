package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.requests.SignUpRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserServiceInt {
    UserDetails loadUserByUsername(String username);
    String signup (AppUser appUser);
}
