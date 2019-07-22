package com.ats.service;

import com.ats.util.RestResponse;

public interface ApplyService {
    RestResponse create(int CvId, int JobID);

    RestResponse confirm(int JobId, int CvId);

    RestResponse deny(int JobId, int CvId);

    boolean check(int JobSeekerID, int JobID);

    RestResponse listJob(int JobSeekerId);

    RestResponse listCv(int JobId);

    RestResponse checkStatusApply(int CvId, int JobId);
}
