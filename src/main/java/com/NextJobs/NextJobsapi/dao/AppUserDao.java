package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserDao extends CrudRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
}
