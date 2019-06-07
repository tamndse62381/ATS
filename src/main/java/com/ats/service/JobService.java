package com.ats.service;

import com.ats.entity.Job;
import org.springframework.stereotype.Service;


@Service
public interface JobService {
	int createJob(Job job);
}
