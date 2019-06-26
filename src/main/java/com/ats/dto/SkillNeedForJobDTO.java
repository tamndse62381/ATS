package com.ats.dto;

import com.ats.entity.Skill;
import lombok.Data;

import java.util.List;

@Data
public class SkillNeedForJobDTO {
    int jobid;
    List<Skill> listSkill;
}
