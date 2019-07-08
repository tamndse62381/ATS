package com.ats.service;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.dto.JobDTO3;
import com.ats.entity.Job;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
public interface JobService {
    int createJob(JobDTO2 job);

    int updateJob(JobDTO2 job);

    List<JobDTO> searchJob(String job, Pageable pageable);

    List<Job> getTop8();

    JobDTO3 getJobDetail(int id);

    int changeStatus(int id , String newStatus);


}
