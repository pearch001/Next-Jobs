package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.model.JwtRequest;
import com.NextJobs.NextJobsapi.model.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface User_Service {

    User saveUser(User user);
    UserDetails loadUserByUsername(String username);
    void login(JwtRequest request) throws Exception;

}
