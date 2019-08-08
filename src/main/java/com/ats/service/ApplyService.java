package com.ats.service;

import com.ats.util.RestResponse;

public interface ApplyService {
    RestResponse create(int CvId, int JobID);

    RestResponse confirm(int ApplyId);

    RestResponse deny(int ApplyId);

    boolean check(int JobSeekerID, int JobID);

    RestResponse listJob(int JobSeekerId);

    RestResponse listCv(int JobId);
}
