package com.ats.service.impl;

import com.ats.entity.Job;
import com.ats.repository.JobRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.service.JobService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	JobRepository jobRepository;

	private static final Logger LOGGER = LogManager.getLogger(JobServiceImpl.class);

	@Override
	public int createJob(Job job) {
		LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
		int result = 0;
		Job newJob;
		try {
			newJob = jobRepository.save(job);
			result = newJob.getId();
			System.out.println("KQ : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("End createJob in Job Service with job name : {}", job.getTitle());
		return result;
	}

	@Override
	public List<Job> searchJob(String job) {
		LOGGER.info("Begin searchJob in Job Service with job name : {}", job);
		int result = 0;
		List<Job> listofJob = new ArrayList<>();
		try {
			listofJob = jobRepository.searchJob(job);

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("End searchJob in Job Service with job name : {}", job);
		return listofJob;
	}

}
