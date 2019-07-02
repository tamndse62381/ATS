package com.ats.service;

import com.ats.entity.Education;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationService {
     List<Education> getListEduByCVId(int id);

     Education createANewEducation(Education newEducation);

     Education editAEducation(Education editedEducation, int id);

     boolean deleteAEducation(int id);
}
