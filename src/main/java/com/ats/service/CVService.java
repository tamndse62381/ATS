package com.ats.service;

import com.ats.entity.Cv;
import org.springframework.stereotype.Service;

@Service
public interface CVService {
    public Cv getCVByCVID(int id);

    public Cv getCvByEmail(String email);
}
