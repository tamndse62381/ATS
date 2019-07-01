package com.ats.service;

import com.ats.dto.CertificationDTO;
import com.ats.entity.Certification;
import com.ats.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificationService {
    public List<Certification> getListCertificationByCVID(int id);

    public void createANewCertification(Certification newCertification);


}
