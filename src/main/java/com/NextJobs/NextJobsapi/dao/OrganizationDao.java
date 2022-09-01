package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.ExVolunteer;
import com.NextJobs.NextJobsapi.model.entities.Organisation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationDao extends CrudRepository<Organisation,Long> {

    Optional<Organisation> findByAppUser(AppUser user);
}
