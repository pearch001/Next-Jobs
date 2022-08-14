package com.NextJobs.NextJobsapi.dao;

import com.NextJobs.NextJobsapi.model.entities.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDao extends CrudRepository<Job,Long> {
    @Query("SELECT CASE WHEN COUNT(j) > 0 THEN TRUE ELSE FALSE END FROM "
            + "job j WHERE j.id = ?1")
    Boolean findByID(Long id);

    @Query("SELECT j FROM "
            + "job j WHERE j.id = ?1")
    Job selectByID(Long id);

    @Query(value = "SELECT * FROM job ORDER BY id OFFSET ?1 ROWS FETCH NEXT 10 ROWS ONLY", nativeQuery = true)
    List<Job> selectByOffset(int id);

    @Query(value = "SELECT * FROM job " +
            "WHERE title LIKE '%' + ?1 + '%' AND " +
            "state LIKE '%' + ?2 + '%' AND " +
            "salary_upper_bound >= ?3 ORDER BY id OFFSET ?4 ROWS FETCH NEXT 10 ROWS ONLY", nativeQuery = true)
    List<Job> searchJobs(String jobTitle, String state, Long salaryBound, int offset);

    @Query(value = "SELECT COUNT(id) FROM job " +
            "WHERE title LIKE '%' + ?1 + '%' AND " +
            "state LIKE '%' + ?2 + '%' AND " +
            "salary_upper_bound >= ?3 ", nativeQuery = true)
    int allSearchedJobs(String jobTitle, String state, Long salaryBound);
}
