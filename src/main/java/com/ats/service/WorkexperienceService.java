package com.ats.service;

import com.ats.entity.Workexperience;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkexperienceService {
    public List<Workexperience> getAll();
}
