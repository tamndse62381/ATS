package com.ats.service.impl;

import com.ats.dto.SkillDTO;
import com.ats.entity.Skillincv;
import com.ats.repository.SkillincvRepository;
import com.ats.service.SkillincvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillincvServiceImpl implements SkillincvService {
    @Autowired
    private SkillincvRepository skillincvRepository;

    @Override
    public List<SkillDTO> getListSkillDTOByCVID(int cvid) {
        List<Skillincv> listSkillincv = skillincvRepository.findListSkillmasterByCVID(cvid);
        if (listSkillincv != null){

        }
        return null;
    }
}
