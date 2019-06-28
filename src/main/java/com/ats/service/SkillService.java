package com.ats.service;

import com.ats.dto.SkillDTO;
import com.ats.entity.Skill;
import org.springframework.stereotype.Service;

@Service
public interface SkillService {
    int addNewSkill(SkillDTO skill);

    int checkSkillBySkillLevel(SkillDTO skill);
}
