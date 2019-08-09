package com.ats.service;

import com.ats.entity.Cv;
import com.ats.dto.JobDTO;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ApplyService {
    RestResponse create(int CvId, int JobID);

    RestResponse confirm(int JobId, int CvId);

    RestResponse deny(int JobId, int CvId);

    boolean check(int JobSeekerID, int JobID);

    RestResponse listJob(int JobSeekerId);

    Page<Cv> listCv(int JobId, Pageable pageable);

    RestResponse checkStatusApply(int CvId, int JobId);

    List<JobDTO> listJobMobile(int JobSeekerId);
}
