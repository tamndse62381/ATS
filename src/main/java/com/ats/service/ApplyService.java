package com.ats.service;

import com.ats.util.RestResponse;

public interface ApplyService {
    RestResponse create(int JobSeekerID, int JobID);

    boolean check(int JobSeekerID, int JobID);
}
