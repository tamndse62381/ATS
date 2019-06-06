package com.ats.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.entity.Job;
import com.ats.repository.JobDao;
import com.ats.service.JobService;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	JobDao jobDao;

	private static final Logger LOGGER = LogManager.getLogger(JobServiceImpl.class);

	@Override
	public int createJob(Job job) {
		LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
		int result = 0;
		Job newJob = new Job();
		try {
			newJob = jobDao.save(job);
			result = newJob.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
		return result;
	}

}
