package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.AppUserDao;
import com.NextJobs.NextJobsapi.dao.ExVolunteerDao;
import com.NextJobs.NextJobsapi.dao.IndividualDao;
import com.NextJobs.NextJobsapi.dao.OrganizationDao;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.ExVolunteer;
import com.NextJobs.NextJobsapi.model.entities.Individual;
import com.NextJobs.NextJobsapi.model.entities.Job;
import com.NextJobs.NextJobsapi.model.requests.ExVolunteerRequest;
import com.NextJobs.NextJobsapi.model.requests.IndividualRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfilesService {

    @Autowired
    private IndividualDao individualDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private ExVolunteerDao exVolunteerDao;

    @Autowired
    private AppUserServiceImpl appUserService;

    public void saveIndividual(IndividualRequest individualRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        AppUser user = appUserService.loadUserByEmail(username);
        var individual = new Individual(individualRequest.getCurrentPosition(),individualRequest.getEducation(),
                individualRequest.getCountry(),individualRequest.getLocation(), individualRequest.getPhoneNumber(),
                individualRequest.getAboutYourself(),individualRequest.getDob(), individualRequest.getWebsiteUrl(), individualRequest.getCvUrl(),
                 individualRequest.getGithubUrl(),individualRequest.getLinkedInUrl(),user,
                individualRequest.getSkills());
        log.info("Saving individual");
        individualDao.save(individual);
    }


    public void saveExVolunteer(ExVolunteerRequest exVolunteerRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        AppUser user = appUserService.loadUserByEmail(username);
        var exVolunteer = new ExVolunteer(exVolunteerRequest.getCurrentPosition(),exVolunteerRequest.getEducation(),
                exVolunteerRequest.getCountry(),exVolunteerRequest.getLocation(), exVolunteerRequest.getPhoneNumber(),
                exVolunteerRequest.getAboutYourself(),exVolunteerRequest.getDob(), exVolunteerRequest.getWebsiteUrl(), exVolunteerRequest.getCvUrl(),
                exVolunteerRequest.getGithubUrl(),exVolunteerRequest.getLinkedInUrl(),user,
                exVolunteerRequest.getSkills());

        log.info("Saving Ex-Volunteer");
        exVolunteerDao.save(exVolunteer);
    }
}
