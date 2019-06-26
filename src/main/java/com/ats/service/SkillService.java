package com.ats.service;

import com.ats.entity.Skill;
import org.springframework.stereotype.Service;

@Service
public interface SkillService {
    int addNewSkill(Skill skill);

    int checkSkillBySkillLevel(Skill skill);
}
