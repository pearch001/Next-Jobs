package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserDao extends CrudRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);

    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.imageUrl = ?2 WHERE a.email = ?1")
    int addImageUrl(String email,String imageUrl);
}
