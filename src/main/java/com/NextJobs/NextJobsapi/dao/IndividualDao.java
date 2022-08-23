package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.AppUser;
import com.NextJobs.NextJobsapi.model.entities.Individual;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IndividualDao extends CrudRepository<Individual,Long> {



    Optional<Individual> findByAppUser(AppUser user);
}
