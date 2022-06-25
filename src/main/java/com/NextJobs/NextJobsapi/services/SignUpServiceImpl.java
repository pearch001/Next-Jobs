package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.requests.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SignUpServiceImpl {

    @Autowired
    private AppUserDao appUserDao;

    public void register(SignUpRequest request) {
        AppUser appUser = new AppUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());
        appUserDao.save(appUser);

    }
}
