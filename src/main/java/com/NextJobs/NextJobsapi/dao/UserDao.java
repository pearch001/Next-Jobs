package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User,Long> {
    Optional<User> findById(Long Id);
    User findByUsername (String username);
}
