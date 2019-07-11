package com.ats.service.impl;

import com.ats.repository.JobseekerlikejobRespository;
import com.ats.service.JobseekerlikejobService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobseekerlikejobServiceImpl implements JobseekerlikejobService {
    @Autowired
    private JobseekerlikejobRespository jobseekerlikejobRespository;

    @Override
    public boolean check(int JobSeekerId, int Jobid) {
        
        return false;
    }

    @Override
    public RestResponse create() {
        return null;
    }
}
