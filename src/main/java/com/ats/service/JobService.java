package com.ats.service;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
public interface JobService {
    int createJob(JobDTO2 job);

    List<JobDTO> searchJob(String job, Pageable pageable);

    List<JobDTO> getTop8();

    int changeStatus(int id , String newStatus);


}
