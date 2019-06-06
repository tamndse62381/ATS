package com.ats.service;

import org.springframework.stereotype.Service;

import com.ats.entity.Job;



@Service
public interface JobService {
	public int createJob(Job job);
}
