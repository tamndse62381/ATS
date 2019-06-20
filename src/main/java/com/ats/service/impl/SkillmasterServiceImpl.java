package com.ats.service.impl;

import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import com.ats.repository.SkillmasterRepository;
import com.ats.service.SkillmasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillmasterServiceImpl implements SkillmasterService {
    @Autowired
    private SkillmasterRepository skillmasterRepository;

    @Override
    public boolean createANewSkillMaster(Skillmaster newSkillmaster) {
        try{
            skillmasterRepository.save(newSkillmaster);
            return true;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Skillmaster> listAll() {
        return skillmasterRepository.findAll();
    }

    @Override
    public boolean editASkillmaster(Skillmaster editedSkillmaster, int id) {
        try {
            Skillmaster skillmaster = skillmasterRepository.getOne(id);
            if (skillmaster != null){
                skillmaster.setSkillName(editedSkillmaster.getSkillName());
                skillmaster.setSkillTypeID(editedSkillmaster.getSkillTypeID());
                skillmasterRepository.save(skillmaster);
                return true;
            }
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Skillmaster> findAllLanguageskill(int id) {
        try {
            return skillmasterRepository.findAllLanguageSkill(id);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
