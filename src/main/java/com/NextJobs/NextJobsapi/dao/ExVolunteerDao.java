package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.ExVolunteer;
import com.NextJobs.NextJobsapi.model.entities.Individual;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExVolunteerDao extends CrudRepository<ExVolunteer, Long> {
    Optional<ExVolunteer> findByAppUser(AppUser user);
}
