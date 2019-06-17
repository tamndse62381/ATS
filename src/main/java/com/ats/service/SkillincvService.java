package com.ats.service;

import com.ats.dto.SkillDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillincvService {
    public List<SkillDTO> getListSkillDTOByCVID(int cvid);
}
