package com.ats.service.impl;

import com.ats.entity.Skill;

import com.ats.repository.SkillRepository;
import com.ats.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    private static final Logger LOGGER = LogManager.getLogger(SkillServiceImpl.class);

    @Override
    public int addNewSkill(Skill skill) {
        LOGGER.info("Begin addNewSkill in Skill Service with skillmaster id {}", skill.getSkillMasterId());
        int skillResult = -1;
        try {
            skillResult = checkSkillBySkillLevel(skill);
            if (skillResult == -1) {
                skill = skillRepository.save(skill);
                skillResult = skill.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End addNewSkill in Skill Service with skillmaster id {}", skill.getSkillMasterId());
        return skillResult;
    }

    @Override
    public int checkSkillBySkillLevel(Skill skill) {
        LOGGER.info("Begin checkSkillBySkillLevel in Skill Service with skill master with skill level {}",
                skill.getSkillMasterId() + "-" + skill.getSkillLevel());
        try {
            Skill skillResult = skillRepository.findSkillbySkillLevel(skill.getSkillLevel(), skill.getSkillMasterId());
            LOGGER.info("End checkSkillBySkillLevel in Skill Service with skill master with skill level {}",
                    skill.getSkillMasterId() + "-" + skill.getSkillLevel());
            if (skillResult != null) {
                return skillResult.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
