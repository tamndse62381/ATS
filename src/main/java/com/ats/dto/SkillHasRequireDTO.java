package com.ats.dto;

import lombok.Data;


@Data
public class SkillHasRequireDTO {
    private int id;
    private  int skillMasterId;
    private int  skillLevel;
    private boolean require;
}
