package com.ats.service;

import com.ats.util.RestResponse;
import org.springframework.stereotype.Service;

@Service
public interface JobseekerlikejobService {
    boolean check(int JobSeekerId, int Jobid);

    RestResponse create(int JobSeekerId, int JobId);
}
