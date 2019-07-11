package com.ats.service.impl;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.dto.JobDTO3;
import com.ats.entity.*;
import com.ats.repository.JobRepository;
import com.ats.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    CityService cityService;
    @Autowired
    SkillService skillService;
    @Autowired
    JoblevelService joblevelService;

    private static final Logger LOGGER = LogManager.getLogger(JobServiceImpl.class);

    @Override
    public int createJob(JobDTO2 job) {
        LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
        int result = -1;
        Job newJob;
        try {
            ModelMapper mapper = new ModelMapper();
            newJob = mapper.map(job, Job.class);
            City city = new City();
            city.setId(newJob.getCityId());

            Company company = new Company();
            company.setId(newJob.getCompanyId());

            Users users = new Users();
            users.setId(newJob.getUserId());

            Joblevel joblevel = new Joblevel();
            joblevel.setId(newJob.getJobLevelId());

            Industry industry = new Industry();
            industry.setId(newJob.getIndustryId());

            newJob.setCityByCityId(city);
            newJob.setUsersByUserId(users);
            newJob.setCompanyByCompanyId(company);
            newJob.setJoblevelByJobLevelId(joblevel);
            newJob.setIndustryByIndustryId(industry);

//            newJob = jobRepository.save(newJob);
            result = jobRepository.save(newJob).getId();
            System.out.println("KQ : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End createJob in Job Service with job name : {}", job.getTitle());
        return result;
    }

    @Override
    public int updateJob(JobDTO2 job) {
        LOGGER.info("Begin updateJob in Job Service with job name : {}", job.getTitle());
        int result = 0;
        Job newJob;
        try {
            newJob = jobRepository.findOne(job.getId());

            newJob.setAddress(job.getAddress());
            newJob.setTitle(job.getTitle());
            newJob.setAdditionalRequest(job.getAdditionalRequest());
            newJob.setNumbeOfRecruitment(job.getNumbeOfRecruitment());
            newJob.setSalaryTo(job.getSalaryTo());
            newJob.setSalaryFrom(job.getSalaryFrom());
            newJob.setWorkingType(job.getWorkingtype());
            newJob.setJobDescription(job.getJobDescription());
            newJob.setYearExperience(job.getYearExperience());

            newJob = jobRepository.save(newJob);
            result = newJob.getId();
            System.out.println("KQ : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End updateJob in Job Service with job name : {}", job.getTitle());
        return result;
    }

    @Override
    public Page<JobDTO> searchJob(String job, int cityid, int industryid, Pageable pageable) {
        LOGGER.info("Begin searchJob in Job Service with job name : {} ", job);
        Page<Job> listofJob;
        Page<JobDTO> listofDTO = null;
        try {
            LOGGER.info("Begin searchJob in Job Repository with job name : {} ", job);
            listofJob = jobRepository.searchJob(job, pageable, "new", new Date(), cityid, industryid);

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<Page<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob, targetListType);
            LOGGER.info("End searchJob in Job Repository with job list size : {} ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in Job Service with job list size : {} ");
        return listofDTO;
    }

    @Override
    public List<String> getALlJobTitle() {
        LOGGER.info("Begin getALlJobTitle in Job Service");
        List<Job> listofJob;
        List<String> listofResult = new ArrayList<>();


        try {
            LOGGER.info("Begin getALlJobTitle in Job Repository ");
            listofJob = jobRepository.findAll();
            LOGGER.info("End getALlJobTitle in Job Repository");
            for (int i = 0; i < listofJob.size(); i++) {
                if (listofJob.get(i).getStatus().matches("new") &&
                        listofJob.get(i).getEndDateForApply().after(new Date())) {
                    LOGGER.info("Add a job to listofResult because job is available " + listofJob.get(i).getTitle());
                    listofResult.add(listofJob.get(i).getTitle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        return listofResult;
    }

    @Override
    public Page<Job> getTop8(Pageable pageable) {
        LOGGER.info("Begin getTop8 in Job Service");
        Page<Job> listofJob = null;
        try {
            LOGGER.info("Begin getTop8 in Job Repository ");
            listofJob = jobRepository.getTop8(pageable, "new", new Date());
            LOGGER.info("End getTop8 in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        return listofJob;
    }

    @Override
    public JobDTO3 getJobDetail(int id) {
        LOGGER.info("Begin getJobDetail in Job Service with id : " + id);
        Job job;
        JobDTO3 jobDTO = null;

        try {
            LOGGER.info("Begin getJobDetail in Job Repository with id : " + id);
            job = jobRepository.findOne(id);
            LOGGER.info("End getJobDetail in Job Repository with id : " + id);
            if (job != null) {
                if (!job.getStatus().matches("new") ||
                        !job.getEndDateForApply().after(new Date())) {
                    job = null;
                }
            }
            List<Job> listJobOfCompany = jobRepository.getJobByCompanyID(job.getCompanyId(), job.getId());
            List<String> listSkillName = skillService.getSkillName(job.getSkillneedforjobsById());
            String jobLevelName = joblevelService.getJobLevelNameById(job.getJobLevelId());
            ModelMapper mapper = new ModelMapper();
            jobDTO = mapper.map(job, JobDTO3.class);

            jobDTO.setListSkillName(listSkillName);
            jobDTO.setListJobSameCompany(listJobOfCompany);
            jobDTO.setJoblevelName(jobLevelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobDetail in Job Service with id : " + id);
        return jobDTO;
    }

    @Override
    public int changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in Account Service with Account id - newStatus : {}", id + newStatus);
        int success;
        success = jobRepository.changeStatus(id, newStatus);
        LOGGER.info("End changeStatus in Account Service with result: {}", success);
        return success;
    }

}
