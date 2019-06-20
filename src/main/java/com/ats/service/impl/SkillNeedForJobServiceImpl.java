package com.ats.service.impl;

import com.ats.entity.Skillneedforjob;
import com.ats.repository.SkillNeedForJobRepository;
import com.ats.service.SkillNeedForJobService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillNeedForJobServiceImpl implements SkillNeedForJobService {

    @Autowired
    SkillNeedForJobRepository skillNeedForJobRepository;

    private static final Logger LOGGER = LogManager.getLogger(SkillNeedForJobServiceImpl.class);

    @Override
    public boolean addSkillForJob(List<Integer> listSkillId, int jobId) {
        LOGGER.info("Begin addSkillForJob in SkillNeedForJob Service with JobID {}", jobId);
        try {

            for (int i = 0; i < listSkillId.size(); i++) {
                Skillneedforjob skillneedforjob = new Skillneedforjob();
                skillneedforjob.setJobid(jobId);
                skillneedforjob.setSkillid(listSkillId.get(i));
                skillNeedForJobRepository.save(skillneedforjob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End addSkillForJob in SkillNeedForJob Service with JobID {}", jobId);
        return false;
    }
}
