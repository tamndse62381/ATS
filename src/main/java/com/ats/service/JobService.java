package com.ats.service;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.dto.JobDTO3;
import com.ats.entity.Job;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public interface JobService {
    int createJob(JobDTO2 job);

    int updateJob(JobDTO2 job);

    Page<JobDTO> searchJob(String job, String cityid, String industryid, Pageable pageable);

    List<String> getALlJobTitle();

    Page<JobDTO> getTop8(Pageable pageable);

    List<JobDTO> getTop8Mobile();

    
    JobDTO3 getJobDetail(int id, int userId);

    int changeStatus(int id, String newStatus);

    Page<JobDTO> suggestJob(int cvid, Pageable pageable);

    Page<JobDTO> suggestJobByJobId(int jobid, Pageable pageable);

    Page<JobDTO> suggestJobByUserId(int userid, Pageable pageable);

    RestResponse findListJobValid(int EmployerId);

    RestResponse findListJobInValid(int EmployerId);

    Page<Job> getAllJob(Pageable pageable, String search, String status);
}
