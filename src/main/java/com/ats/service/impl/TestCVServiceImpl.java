package com.ats.service.impl;

import com.ats.dto.JobDTO2;
import com.ats.entity.Job;

import com.ats.repository.JobRepository;
import com.ats.service.*;

import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCVServiceImpl implements TestCVService {
    @Autowired
    JobRepository jobRepository;

    @Override
    public RestResponse suggest() {
        List<JobDTO2> result = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        if(jobList.size() > 0){
            for(int i = 0; i < jobList.size();i++){
                JobDTO2 tmp = new JobDTO2();
                tmp.setId(jobList.get(i).getId());

                result.add(tmp);
            }

        }


        return new RestResponse(true, "Thành công!!!", result);


    }
}
