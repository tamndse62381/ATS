package com.ats.ws;

import com.ats.entity.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.service.JobService;
import com.ats.util.RestResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobWS {

	@Autowired
	JobService jobService;

	private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

	@ResponseBody
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping(value = "/create")
	public RestResponse createJob(@RequestBody Job job) {
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
