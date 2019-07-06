package com.ats.service;

import com.ats.dto.CVDTO;
import com.ats.entity.Cv;
import com.ats.form.CreateCVForm;
import com.ats.model.FileModel;
import org.springframework.http.ResponseEntity;

public interface CVService {
    ResponseEntity<Cv> getCVByCVID(int id);

    Cv getCvByEmail();

    boolean create(CVDTO newCV, FileModel file);

    boolean createTemp(CVDTO newCV);

    boolean createTemp1(CreateCVForm newCv);

    boolean edit(CVDTO editedCv);

    boolean delete(int id);
}
