package com.ats.ws.impl;

import com.ats.entity.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.service.JobService;
import com.ats.util.RestResponse;
import com.ats.ws.JobWS;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class JobWSImpl implements JobWS {

	@Autowired
	JobService jobService;

	private static final Logger LOGGER = LogManager.getLogger(AccountWSImpl.class);

	@ResponseBody
	@Override
	public RestResponse createJob(@Valid @RequestBody Job job) {
		System.out.println("Job Title : " + job.getTitle());
		System.out.println("Job salary : " + job.getSalaryTo());
		LOGGER.info("Begin createJob in JobWS with Job title : {}" + job.getTitle());
		int result = 0;
		try {
			result = jobService.createJob(job);
			if (result > 0) {
				return new RestResponse(true, "Create New Job Successfull", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("End createJob in JobWS with Job title : {}" + job.getTitle());
		return new RestResponse(false, "Fail To Create New Job ", null);

	}

}
