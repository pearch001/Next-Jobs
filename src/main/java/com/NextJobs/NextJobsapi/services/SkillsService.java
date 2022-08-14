package com.NextJobs.NextJobsapi.services;

import com.NextJobs.NextJobsapi.dao.IndividualDao;
import com.NextJobs.NextJobsapi.dao.SkillsDao;
import com.NextJobs.NextJobsapi.model.entities.Individual;
import com.NextJobs.NextJobsapi.model.entities.Skills;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SkillsService {

    @Autowired
    private SkillsDao skillsDao;

    public void saveSkills(Skills skills) {
        log.info("Saving individual");
        skillsDao.save(skills);
    }

    public List<Skills> loadSkills(){
        return (List<Skills>) skillsDao.findAll();
    }


}
