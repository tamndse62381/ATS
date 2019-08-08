package com.ats.service;

import com.ats.dto.JobDTO;
import com.ats.util.RestResponse;

import java.util.List;

public interface ApplyService {
    RestResponse create(int CvId, int JobID);

    RestResponse confirm(int ApplyId);

    RestResponse deny(int ApplyId);

    boolean check(int JobSeekerID, int JobID);

    RestResponse listJob(int JobSeekerId);

    List<JobDTO> listJobMobile(int JobSeekerId);

    RestResponse listCv(int JobId);
}
