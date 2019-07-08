package com.ats.service;

import com.ats.dto.CVDTO;
import com.ats.entity.Cv;
import com.ats.form.CreateCVForm;
import com.ats.model.FileModel;
import com.ats.util.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.MalformedURLException;

public interface CVService {
    RestResponse getCVByCVID(int id);

    Cv getCvByEmail();

    RestResponse create(CreateCVForm newCV);

    boolean edit(CVDTO editedCv);

    RestResponse delete(int id);

    RestResponse getlistCvByUserId(int id);
}
