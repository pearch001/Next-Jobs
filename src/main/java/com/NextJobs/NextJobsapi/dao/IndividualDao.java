package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.Individual;
import org.springframework.data.repository.CrudRepository;

public interface IndividualDao extends CrudRepository<Individual,Long> {
}
