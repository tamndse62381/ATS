package com.ats.ws;

import com.ats.dto.JobDTO;
import com.ats.entity.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.service.JobService;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobWS {
    @Autowired
    JobService jobService;

    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create")
    public RestResponse createJob(@RequestBody Job job) {
        LOGGER.info("Begin createJob in JobWS with Job title : {}" + job.getTitle());
        int result = 0;
        try {
            Date date = new Date();
            Timestamp ts=new Timestamp(date.getTime());
            System.out.println(ts);
            job.setCreatedDate(ts);
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

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/search")
    @ResponseBody
    public List<JobDTO> searchJob(@RequestParam(value = "search") String search, @PageableDefault Pageable pageable) {
        LOGGER.info("Begin searchJob in JobWS  with Search value : {}" + search);
        List<JobDTO> listJob = new ArrayList<>();
        try {
            listJob = jobService.searchJob(search, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in JobWS with Search value : {}" + search);
        return listJob;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/changeJobStatus")
    @ResponseBody
    public List<Job> changeJobStatus(@RequestParam(value = "search") String search) {
        LOGGER.info("Begin changeJobStatus in JobWS with Search value : {}" + search);
        List<Job> listUser = new ArrayList<>();
        try {
//            listUser = jobService.searchJob(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changeJobStatus in JobWS with Search value : {}" + search);
        return listUser;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTop8")
    @ResponseBody
    public List<JobDTO> getTop8() {
        LOGGER.info("Begin getTop8 in JobWS ");
        List<JobDTO> listJobs = new ArrayList<>();
        try {
            listJobs = jobService.getTop8();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in JobWS ");
        return listJobs;
    }

}
