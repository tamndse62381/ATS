package com.ats.service;

import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillmasterService {
     boolean createANewSkillMaster(Skillmaster skillmaster);

     List<Skillmaster> listAll();

     boolean editASkillmaster(Skillmaster editedSkillmaster, int id);

     List<Skillmaster> findAllLanguageskill(int id);
}
