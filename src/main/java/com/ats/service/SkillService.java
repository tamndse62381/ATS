package com.ats.service;

import com.ats.entity.Skill;
import org.springframework.stereotype.Service;

@Service
public interface SkillService {
    public int addNewSkill(Skill skill);

    public boolean checkSkillBySkillLevel(Skill skill);
}
