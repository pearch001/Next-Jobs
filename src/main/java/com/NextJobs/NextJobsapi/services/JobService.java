package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.JobDao;
import com.NextJobs.NextJobsapi.model.dtos.JobDto;
import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.Job;

import com.NextJobs.NextJobsapi.model.entities.Organisation;
import com.NextJobs.NextJobsapi.model.requests.CreateJobRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class JobService {
    private final JobDao jobDao;

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private ProfilesService profilesService;



    public Job saveJob(CreateJobRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        AppUser user = appUserService.loadUserByEmail(username);

        var organization = profilesService.getOrgaizationFromUser(user);

        log.info("Saving Job");
        return jobDao.save(new Job(request.getDescription(),request.getTitle(),request.getType(),request.getLocation(), request.getExperience(), request.getSkills(),
                request.getApplicationDeadline(), organization));


    }

    public List<Job> loadJob(int offset) {
        return jobDao.selectByOffset(offset);
    }


    public List<Job> searchJobs(String jobTitle, String city, Long salaryBound, int offset) {
        if (jobTitle == null){
            jobTitle = "";
        }else {
            jobTitle = jobTitle.trim();
        }
        if (city == null){
            city = "";
        }else {
            city = city.trim();
        }
        if (salaryBound == null){
            salaryBound = Long.MIN_VALUE;
        }
            return (List<Job>) jobDao.searchAllByTitleContainingIgnoreCase(jobTitle);
    }


    public int allSearchedJobs(String jobTitle, String city, Long salaryBound) {
        if (jobTitle == null){
            jobTitle = "";
        }else {
            jobTitle = jobTitle.trim();
        }
        if (city == null){
            city = "";
        }else {
            city = city.trim();
        }
        if (salaryBound == null){
            salaryBound = Long.MIN_VALUE;
        }
        return jobDao.allSearchedJobs(jobTitle, city, salaryBound);
    }


    public List<Job> loadAllJob() {
        return (List<Job>) jobDao.findAll();
    }


    public void deleteJob(Long id) throws IllegalStateException{
        Boolean value = jobDao.findByID(id);
        Job job = jobDao.selectByID(id);
        if (value) {
            jobDao.delete(job);
        } else {
            throw new IllegalStateException("Job not found");
        }
    }
}
