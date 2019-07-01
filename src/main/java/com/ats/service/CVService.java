package com.ats.service;

import com.ats.dto.CVDTO;
import com.ats.entity.Cv;
import org.springframework.http.ResponseEntity;

public interface CVService {
    ResponseEntity<Cv> getCVByCVID(int id);

    Cv getCvByEmail();

    boolean create(CVDTO newCV);

    boolean edit(CVDTO editedCv);

    boolean delete(int id);
}
