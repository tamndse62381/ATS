package com.ats.service.impl;

import com.ats.entity.Workexperience;
import com.ats.repository.WorkexperienceRepository;
import com.ats.service.WorkexperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkexperienceServiceImpl implements WorkexperienceService {
    @Autowired
    private WorkexperienceRepository workexperienceRepository;


    @Override
    public List<Workexperience> getAll() {
        try {
             return workexperienceRepository.findAll();
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }
}
