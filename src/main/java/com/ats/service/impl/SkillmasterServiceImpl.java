package com.ats.service.impl;

import com.ats.dto.SkillMasterDTO;
import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import com.ats.repository.SkillmasterRepository;
import com.ats.service.SkillmasterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillmasterServiceImpl implements SkillmasterService {
    @Autowired
    private SkillmasterRepository skillmasterRepository;

    private static final Logger LOGGER = LogManager.getLogger(SkillmasterServiceImpl.class);

    @Override
    public boolean createANewSkillMaster(Skillmaster newSkillmaster) {
        try {
            skillmasterRepository.save(newSkillmaster);
            return true;
        } catch (RuntimeException e) {
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
            if (skillmaster != null) {
                skillmaster.setSkillName(editedSkillmaster.getSkillName());
                skillmaster.setSkillTypeId(editedSkillmaster.getSkillTypeId());
                skillmasterRepository.save(skillmaster);
                return true;
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Skillmaster> findAllLanguageskill(int id) {
        try {
            return skillmasterRepository.findAllLanguageSkill(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<String> getSkillNameById(List<Integer> integerList) {
        LOGGER.info("Begin getSkillNameById in SkillMaster Service with List size : ", integerList.size());
        List<String> listSkillName = new ArrayList<>();

        try {
            for (int i = 0; i < integerList.size(); i++) {
                Skillmaster skillmaster = skillmasterRepository.findOne(integerList.get(i));
                listSkillName.add(skillmaster.getSkillName());
            }
            Set<String> set = new HashSet<>(listSkillName);
            listSkillName.clear();
            listSkillName.addAll(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getSkillNameById in SkillMaster Service with List size : ", integerList.size());
        return listSkillName;
    }
}
