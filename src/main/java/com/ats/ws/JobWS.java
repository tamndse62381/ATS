package com.ats.ws;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.entity.Job;
import com.ats.entity.Joblevel;
import com.ats.entity.Skillmaster;
import com.ats.service.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.util.RestResponse;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/job")
public class JobWS {
    @Autowired
    JobService jobService;
    @Autowired
    SkillService skillService;
    @Autowired
    SkillNeedForJobService skillNeedForJobService;
    @Autowired
    JoblevelService joblevelService;
    @Autowired
    SkillmasterService skillmasterService;

    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create")
    public RestResponse createJob(@RequestBody JobDTO2 job) {
        LOGGER.info("Begin createJob in JobWS with Job title : {}" + job.getTitle());
        int result = 0;

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        try {
            job.setCreatedDate(dt);
            c.add(Calendar.DATE, 30);
            job.setEndDateForApply(c.getTime());
            job.setStatus("new");
            result = jobService.createJob(job);

            if (result > 0) {
                return new RestResponse(true, "Create New Job Successfull", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Job ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/search")
    @ResponseBody
    public RestResponse searchJob(@RequestParam(value = "search") String search, @PageableDefault Pageable pageable) {
        LOGGER.info("Begin searchJob in JobWS  with Search value : {}" + search);
        List<JobDTO> listJob = new ArrayList<>();
        try {
            listJob = jobService.searchJob(search, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in JobWS with Search value : {}" + search);
        return new RestResponse(true, "get searchJob Successfull with list Size : " + listJob.size(), listJob);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeJobStatus")
    @ResponseBody
    public RestResponse changeJobStatus(@RequestBody JobDTO2 job) {
        LOGGER.info("Begin changeJobStatus in JobWS with Search value : {}" + job.getId());
        int success;
        try {
            success = jobService.changeStatus(job.getId(), job.getStatus());
            if (success > 0) {
                return new RestResponse(true, "changeStatus Successful with status " + job.getStatus(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changeJobStatus in JobWS with Search value : {}" + job.getId());
        return new RestResponse(false, "changeStatus Fail", null);

    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getTop8")
    public RestResponse getTop8() {
        LOGGER.info("Begin getTop8 in JobWS ");
        List<Job> listJobs = new ArrayList<>();
        try {
            listJobs = jobService.getTop8();
            return new RestResponse(true, "get Top8 Successfull with list size is : " +
                    listJobs.size(), listJobs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in JobWS ");
        return new RestResponse(false, "get Top8 Fail", listJobs);
    }




    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getJobDetail", produces = "application/json;charset=UTF-8")
    public RestResponse getJobDetail(@RequestParam("id") int id) {
        LOGGER.info("Begin getJobDetail in JobWS with id " + id);
        Job job;
        try {
            job = jobService.getJobDetail(id);
            if (job != null) {
                return new RestResponse(true, "Get job Detail with job id : " + id, job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobDetail in JobWS with id " + id);

        return new RestResponse(false, "Job is Not Available : ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getJobComponent")
    public RestResponse getJobComponent(){
        LOGGER.info("Begin getJobComponent in JobWS");
        List<Skillmaster> listSkillMaster;
        List<String> listSkillName = new ArrayList<>();
        List<Joblevel> listJobLevel;
        try {
            listSkillMaster = skillmasterService.listAll();
            for (int i = 0; i < listSkillMaster.size(); i++) {
                listSkillName.add(listSkillMaster.get(i).getSkillName());
            }

            listJobLevel = joblevelService.getAllJobLevel();
            HashMap<String,List> map = new HashMap<>();
            map.put("skillname", listSkillName);
            map.put("level" , listJobLevel);
                return new RestResponse(true, "Get job Component Successful", map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobComponent in JobWS");

        return new RestResponse(false, "Job is Not Available : ", null);
    }
}
