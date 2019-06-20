package com.ats.service;

import com.ats.entity.Certification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificationService {
    public List<Certification> getListCertificationByCVID(int id);

    public void createANewCertification(Certification newCertification);
}
