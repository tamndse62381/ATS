package com.ats.service.impl;

import com.ats.dto.SkillMasterDTO;
import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import com.ats.repository.SkillmasterRepository;
import com.ats.service.SkillmasterService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public List<SkillMasterDTO> listAll() {
        ModelMapper mapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<SkillMasterDTO>>() {
        }.getType();
        List<SkillMasterDTO> listofDTO;
        List<Skillmaster> listSkillMaster = skillmasterRepository.findAll();
        listofDTO = mapper.map(listSkillMaster, targetListType);
        return listofDTO;
    }

    @Override
    public boolean editASkillmaster(Skillmaster editedSkillmaster, int id) {
        try {
            Skillmaster skillmaster = skillmasterRepository.getOne(id);
            if (skillmaster != null){
                skillmaster.setSkillName(editedSkillmaster.getSkillName());
                skillmaster.setSkillTypeId(editedSkillmaster.getSkillTypeId());
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
