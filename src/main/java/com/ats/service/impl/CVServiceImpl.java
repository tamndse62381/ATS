package com.ats.service.impl;

import com.ats.repository.CVRepository;
import com.ats.service.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CVServiceImpl implements CVService {
    @Autowired
    private CVRepository cvRepository;


}
