package com.ats.service.impl;

import com.ats.repository.ApplyRepository;
import com.ats.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyRepository applyRepository;
}
