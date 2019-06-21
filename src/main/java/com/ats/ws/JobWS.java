package com.ats.ws;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.entity.Skill;
import com.ats.service.SkillNeedForJobService;
import com.ats.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.service.JobService;
import com.ats.util.RestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobWS {
    @Autowired
    JobService jobService;
    @Autowired
    SkillService skillService;
    @Autowired
    SkillNeedForJobService skillNeedForJobService;

    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create")
    public RestResponse createJob(@RequestBody JobDTO2 job) {
        LOGGER.info("Begin createJob in JobWS with Job title : {}" + job.getTitle());
        int result = 0;
        List<Integer> listSkillId = new ArrayList<Integer>();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        try {
            job.setCreatedDate(dt);
            c.add(Calendar.DATE, 30);
            job.setEndDateForApply(c.getTime());
            job.setStatus("new");
            result = jobService.createJob(job);
            List<Skill> listSkill = job.getListSkill();
            for (int i = 0; i < listSkill.size(); i++) {
                listSkillId.add(skillService.addNewSkill(listSkill.get(i)));
            }
            LOGGER.info("End createJob in JobWS with Job title : {}" + job.getTitle());
            boolean finish = skillNeedForJobService.addSkillForJob(listSkillId, result);
            if (result > 0 && finish) {
                return new RestResponse(true, "Create New Job Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
