package com.ats.service.impl;

import com.ats.entity.Cv;
import com.ats.repository.CVRepository;
import com.ats.service.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CVServiceImpl implements CVService {
    @Autowired
    private CVRepository cvRepository;

    @Override
    public Cv getCVByCVID(int id) {
        return cvRepository.findOne(id);
    }

    @Override
    public Cv getCvByEmail(String email) {
//        return cvRepository.find(email);
        return null;
    }
}
