package com.ats.service;

import com.ats.dto.CVDTO;
import com.ats.entity.Cv;
import org.springframework.http.ResponseEntity;

public interface CVService {
    ResponseEntity<CVDTO> getCVByCVID(int id);

    Cv getCvByEmail(String email);

    boolean create(CVDTO newCV);

    boolean edit(CVDTO editedCv);
}
