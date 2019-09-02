package com.ats.dto;

import com.ats.enummerator.WorkingType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JobDTO4 {
    private Integer id;
    private List<SkillDTO> listSkill;

}
