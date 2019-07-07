package com.ats.service;

import com.ats.dto.CVDTO;
import com.ats.entity.Cv;
import com.ats.form.CreateCVForm;
import com.ats.model.FileModel;
import com.ats.util.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

public interface CVService {
    ResponseEntity<Cv> getCVByCVID(int id);

    Cv getCvByEmail();

    boolean create(CVDTO newCV, FileModel file);

    boolean createTemp(CVDTO newCV);

    boolean createTemp1(CreateCVForm newCv);

    boolean edit(CVDTO editedCv);

    RestResponse delete(int id);

    RestResponse getlistCvByUserId(int id);
}
