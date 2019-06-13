package com.ats.service;

import com.ats.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface JobService {
	int createJob(Job job);
	List<Job> searchJob(String job);
}
