package com.ats.service;

import com.ats.entity.Education;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationService {
    public ResponseEntity<List<Education>> getAllByCVId(int id);

    public Education createANewEducation(Education newEducation);

    public Education editAEducation(Education editedEducation, int id);

    public boolean deleteAEducation(int id);
}
