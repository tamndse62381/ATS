package com.ats.service;

import com.ats.entity.Workexperience;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkexperienceService {
    public List<Workexperience> getAll();

    public List<Workexperience> getByCVID(int id);

    public ResponseEntity<Workexperience> editByID(Workexperience editedWorkexperience, int id);

    public ResponseEntity<Workexperience> deleteByID(Workexperience deletedWorkexperience, int id);

    public boolean createANewWorkExperience(Workexperience newWorkexperience);
}
