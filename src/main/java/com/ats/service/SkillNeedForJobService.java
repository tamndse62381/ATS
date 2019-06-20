package com.ats.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillNeedForJobService {
    boolean addSkillForJob(List<Integer> listSkillId , int jobId);
}
