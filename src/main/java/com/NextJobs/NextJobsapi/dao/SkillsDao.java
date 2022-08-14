package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.Skills;
import org.springframework.data.repository.CrudRepository;

public interface SkillsDao extends CrudRepository<Skills,Long> {
}
