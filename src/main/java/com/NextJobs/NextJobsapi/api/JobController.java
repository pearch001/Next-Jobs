package com.NextJobs.NextJobsapi.api;


import com.NextJobs.NextJobsapi.model.entities.Job;
import com.NextJobs.NextJobsapi.services.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/jobs")
@RestController
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping(value = "/admin/add")
    public ResponseEntity<Job> addJob(@RequestBody Job job){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/job/add").toUriString());
        return ResponseEntity.created(uri).body(jobService.saveJob(job));
    }

    @GetMapping(value = "/{offset}")
    public List<Job> getJobs(@PathVariable int offset){
        return jobService.loadJob(offset);
    }

    @GetMapping(value = "/count")
    public int countJobs(){ return jobService.loadAllJob().size();}

    @GetMapping(value = "/count/{jobTitle}")
    public int countSearchedJobs(@PathVariable("jobTitle") String jobTitle,
                                 @RequestParam(required = false) String city,
                                 @RequestParam(required = false) Long salaryBound){

        return jobService.allSearchedJobs(jobTitle,city,salaryBound);
    }

    @GetMapping(value = "/search/{jobTitle}")
    public List<Job> getJobs(@PathVariable("jobTitle") String jobTitle,
                                 @RequestParam(required = false) String city,
                                 @RequestParam(required = false) Long salaryBound,
                                 @RequestParam(required = false) Integer offset){
        if (offset == null){
            offset = 0;
        }
        return jobService.searchJobs(jobTitle,city,salaryBound,offset);
    }

    @DeleteMapping(value = "/admin/{Id}")
    public ResponseEntity<?> deleteJob(@PathVariable("Id") Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
}
