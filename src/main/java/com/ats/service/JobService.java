package com.ats.service;

import com.ats.dto.JobDTO;
import com.ats.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface JobService {
	int createJob(Job job);
	List<JobDTO> searchJob(String job);
	List<JobDTO> getTop8();


}
