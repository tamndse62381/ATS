package com.ats.ws.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.entity.Job;
import com.ats.service.JobService;
import com.ats.util.RestResponse;
import com.ats.ws.JobWS;

public class JobWSImpl implements JobWS {

	@Autowired
	JobService jobService;

	private static final Logger LOGGER = LogManager.getLogger(AccountWSImpl.class);

	@Override
	public RestResponse createJob(Job job) {
		LOGGER.info("Begin createJob in JobWS with Job title : {}" + job.getTitle());
		int result = 0;
		try {
			result = jobService.createJob(job);
			if (result == 1) {
				return new RestResponse(true, "Create New Job Successfull", null);
			} else {
				return new RestResponse(false, "Fail To Create New Job Successfull", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("End createJob in JobWS with Job title : {}" + job.getTitle());
		return null;
	}

}
