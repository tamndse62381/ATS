package com.ats.service;

import com.ats.entity.Skillneedforjob;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SkillNeedForJobService {
    boolean addSkillForJob(Map<Integer, Boolean> skillNeedForJob , int jobId);

    int checkSkillNeedForJob(Skillneedforjob entity);

    boolean updateSkillForJob(List<Integer> listSkillId , int jobId);
}
