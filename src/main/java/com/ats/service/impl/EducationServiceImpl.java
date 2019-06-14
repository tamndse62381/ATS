package com.ats.service.impl;

import com.ats.entity.Education;
import com.ats.repository.EducationRepository;
import com.ats.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationRepository educationRepository;


    @Override
    public ResponseEntity<List<Education>> getAllByCVId(int id) {
        List<Education> list = educationRepository.findAllEduByCVID(id);
        if (!list.isEmpty())
            return ResponseEntity.ok().body(list);
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public Education createANewEducation(Education newEducation) {
        try {
            educationRepository.save(newEducation);
            return newEducation;
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Education editAEducation(Education editedEducation, int id) {
        Education edu = educationRepository.findOne(id);
        if (edu != null){
            // get set bi loi nha
        }
        return null;
    }

    @Override
    public boolean deleteAEducation(int id) {
        Education edu = educationRepository.findOne(id);
        if (edu != null){
            educationRepository.delete(edu);
            return true;
        }
        return false;
    }
}
