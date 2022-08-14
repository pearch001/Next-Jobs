package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.Organisation;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationDao extends CrudRepository<Organisation,Long> {
}
