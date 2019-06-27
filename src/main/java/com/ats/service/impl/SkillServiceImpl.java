package com.ats.service.impl;

import com.ats.dto.SkillDTO;
import com.ats.entity.Skill;

import com.ats.entity.Skillmaster;
import com.ats.repository.SkillRepository;
import com.ats.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
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
    public int addNewSkill(SkillDTO skilldto) {
        LOGGER.info("Begin addNewSkill in Skill Service with skillmaster id {}", skilldto.getSkillMasterId());
        int skillResult = -1;
        Skill skill = new Skill();
        try {
            skillResult = checkSkillBySkillLevel(skilldto);
            ModelMapper mapper = new ModelMapper();
            skill = mapper.map(skilldto,Skill.class);
            if (skillResult == -1) {
                Skillmaster skillmaster = new Skillmaster();
                skillmaster.setId(skill.getSkillMasterId());
                skill.setSkillmasterBySkillMasterId(skillmaster);
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
    public int checkSkillBySkillLevel(SkillDTO skill) {
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
