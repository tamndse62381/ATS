package com.ats.ws;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.dto.JobDTO3;
import com.ats.dto.SkillMasterDTO;
import com.ats.entity.*;
import com.ats.service.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ats.util.RestResponse;
import org.springframework.data.domain.Page;
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
    @Autowired
    CityService cityService;
    @Autowired
    IndustryService industryService;

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
            List<Integer> listSkillId = new ArrayList<>();
            for (int i = 0; i < job.getListSkill().size(); i++) {
                listSkillId.add(skillService.addNewSkill(job.getListSkill().get(i)));
            }
            boolean finish = skillNeedForJobService.addSkillForJob(listSkillId, result);
            if (finish) {
                return new RestResponse(true, "Create New Job Successfull", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Job ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/update")
    public RestResponse updateJob(@RequestBody JobDTO2 job) {
        LOGGER.info("Begin updateJob in JobWS with Job title : {}" + job.getTitle());
        int result = 0;

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        try {
            job.setEndDateForApply(c.getTime());
            result = jobService.updateJob(job);

            if (result > 0) {
                return new RestResponse(true, "Update Job Successfull", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Update Job ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/search")
    @ResponseBody
    public RestResponse searchJob(@RequestParam(value = "search") String search,
                                  @RequestParam(value = "city") String city,
                                  @RequestParam(value = "industry") String industry,
                                  @PageableDefault Pageable pageable) {
        LOGGER.info("Begin searchJob in JobWS  with Search value : {}" + search);
        Page<JobDTO> listJob = null;
        try {
            listJob = jobService.searchJob(search, city, industry, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in JobWS with Search value : {}" + search);
        return new RestResponse(true, "get searchJob Successfull with list Size : ", listJob);
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
    public RestResponse getTop8(@PageableDefault Pageable pageable) {
        LOGGER.info("Begin getTop8 in JobWS ");
        Page<Job> listJobs = null;
        try {
            listJobs = jobService.getTop8(pageable);
            return new RestResponse(true, "get Top8 Successfull with list size is : ", listJobs);
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
        JobDTO3 job;
        try {
            job = jobService.getJobDetail(id);
            System.out.println(job.getCreatedDate());
            System.out.println(job.getEndDateForApply());
            LOGGER.info("End getJobDetail in JobWS with id " + id);
            if (job != null) {
                return new RestResponse(true, "Get job Detail with job id : " + id, job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new RestResponse(false, "Job is Not Available : ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getJobComponent")
    public RestResponse getJobComponent() {
        LOGGER.info("Begin getJobComponent in JobWS");
        List<SkillMasterDTO> listSkillMaster;
        List<Joblevel> listJobLevel;
        List<City> listCity;
        List<Industry> listIndustry;
        try {
            listSkillMaster = skillmasterService.listAll();
            listJobLevel = joblevelService.getAllJobLevel();
            listCity = cityService.getAllCity();
            listIndustry = industryService.getAll();
            HashMap<String, List> map = new HashMap<>();
            map.put("skillname", listSkillMaster);
            map.put("level", listJobLevel);
            map.put("city", listCity);
            map.put("industry", listIndustry);
            return new RestResponse(true, "Get job Component Successful", map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobComponent in JobWS");

        return new RestResponse(false, "Job is Not Available : ", null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getSearchComponent")
    public RestResponse getSearchComponent() {
        LOGGER.info("Begin getSearchComponent in JobWS");
        List<SkillMasterDTO> listSkillMaster;
        List<Joblevel> listJobLevel;
        List<String> listTitle;
        List<Object> listAll = new ArrayList<>();
        List<City> listCity;
        List<Industry> listIndustry;
        try {
            listSkillMaster = skillmasterService.listAll();
            listTitle = jobService.getALlJobTitle();
            listJobLevel = joblevelService.getAllJobLevel();
            listCity = cityService.getAllCity();
            listIndustry = industryService.getAll();
            HashMap<String, List> map = new HashMap<>();
            for (int i = 0; i < listJobLevel.size(); i++) {
                listAll.add(listJobLevel.get(i).getJobLevelName());
            }
            for (int i = 0; i < listTitle.size(); i++) {
                listAll.add(listTitle.get(i));
            }
            for (int i = 0; i < listSkillMaster.size(); i++) {
                listAll.add(listSkillMaster.get(i).getSkillName());
            }
            map.put("all", listAll);
            map.put("city", listCity);
            map.put("industry", listIndustry);


            return new RestResponse(true, "Get job Component Successful", map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getSearchComponent in JobWS");

        return new RestResponse(false, "Job is Not Available : ", null);
    }
}
