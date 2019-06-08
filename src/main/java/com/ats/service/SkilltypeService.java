package com.ats.service;

import com.ats.entity.Skilltype;
import org.springframework.stereotype.Service;

@Service
public interface SkilltypeService {
    public boolean createANewSkilltype(Skilltype newSkilltype);

    public boolean editASkilltype(Skilltype editedSkilltype, int id);
}
