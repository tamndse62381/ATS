package com.ats.ws;

import com.ats.entity.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.service.JobService;
import com.ats.util.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobWS {

	@Autowired
	JobService jobService;

	private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

	@ResponseBody
	@CrossOrigin(origins = "localhost:8090")
	@PostMapping(value = "/create")
	public RestResponse createJob(@RequestBody Job job) {
		LOGGER.info("Begin createJob in JobWS with Job title : {}" + job.getTitle());
		int result = 0;
		try {
            java.util.Date date = new java.util.Date();
		    job.setCreatedDate(date);
		    job.setStatus("new");
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
	@CrossOrigin(origins = "localhost:8090")
	@GetMapping(value = "/search")
	@ResponseBody
	public List<Job> searchJob(@RequestParam(value = "search") String search) {
		LOGGER.info("Begin searchJob in JobWS  with Search value : {}" + search);
		List<Job> listJob = new ArrayList<>();
		try {
            listJob = jobService.searchJob(search);
		}catch (Exception e){
			e.printStackTrace();
		}
		LOGGER.info("End searchJob in JobWS with Search value : {}" + search);
		return listJob;
	}

    @CrossOrigin(origins = "localhost:8090")
    @GetMapping(value = "/changeJobStatus")
    @ResponseBody
    public List<Job> changeJobStatus(@RequestParam(value = "search") String search) {
        LOGGER.info("Begin changeJobStatus in JobWS with Search value : {}" + search);
        List<Job> listUser = new ArrayList<>();
        try {
            listUser = jobService.searchJob(search);
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info("End changeJobStatus in JobWS with Search value : {}" + search);
        return listUser;
    }

}
