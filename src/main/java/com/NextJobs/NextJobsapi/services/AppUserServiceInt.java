package com.NextJobs.NextJobsapi.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserServiceInt {
    UserDetails loadUserByUsername(String username);
}
