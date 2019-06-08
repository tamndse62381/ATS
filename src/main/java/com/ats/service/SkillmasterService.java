package com.ats.service;

import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillmasterService {
    public boolean createANewSkillMaster(Skillmaster skillmaster);

    public List<Skillmaster> listAll();

    public boolean editASkillmaster(Skillmaster editedSkillmaster, int id);
}
