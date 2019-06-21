package com.ats.service.impl;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.entity.City;
import com.ats.entity.Company;
import com.ats.entity.Job;
import com.ats.repository.JobRepository;
import com.ats.service.CityService;
import com.ats.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.service.JobService;

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

    private static final Logger LOGGER = LogManager.getLogger(JobServiceImpl.class);

    @Override
    public int createJob(JobDTO2 job) {
        LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
        int result = 0;
        Job newJob;
        try {
            ModelMapper mapper = new ModelMapper();
            Job jobEntity = mapper.map(job, Job.class);
            newJob = jobRepository.save(jobEntity);
            result = newJob.getId();
            System.out.println("KQ : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End createJob in Job Service with job name : {}", job.getTitle());
        return result;
    }

    @Override
    public List<JobDTO> searchJob(String job, Pageable pageable) {
        LOGGER.info("Begin searchJob in Job Service with job name : {} ", job);
        List<Job> listofJob = new ArrayList<>();
        List<Job> listofResult = new ArrayList<>();
        List<JobDTO> listofDTO = new ArrayList<>();
        try {
            LOGGER.info("Begin searchJob in Job Repository with job name : {} ", job);
            listofJob = jobRepository.searchJob(job);
            for (int i = 0; i < listofJob.size(); i++) {
                if (listofJob.get(i).getStatus().matches("new") &&
                        listofJob.get(i).getEndDateForApply().after(new Date())) {
                    LOGGER.info("Add a job to listofResult because job is available " + listofJob.get(i).getTitle());
                    listofResult.add(listofJob.get(i));
                }
            }
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofResult, targetListType);
            LOGGER.info("End searchJob in Job Repository with job list size : {} ", listofJob.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in Job Service with job list size : {} ", listofJob.size());
        return listofDTO;
    }

    @Override
    public List<JobDTO> getTop8() {
        LOGGER.info("Begin getTop8 in Job Service");
        List<Job> listofJob;
        List<Job> listofResult = new ArrayList<>();
        Company company;
        City city;
        List<JobDTO> listofDTO = new ArrayList<>();
        try {
            LOGGER.info("Begin getTop8 in Job Repository ");
            listofJob = jobRepository.getTop8();
            LOGGER.info("End getTop8 in Job Repository");
            for (int i = 0; i < listofJob.size(); i++) {
                if (listofJob.get(i).getStatus().matches("new") &&
                        listofJob.get(i).getEndDateForApply().after(new Date())) {
                    LOGGER.info("Add a job to listofResult because job is available " + listofJob.get(i).getTitle());
                    listofResult.add(listofJob.get(i));
                }
            }
            ModelMapper mapper = new ModelMapper();
            Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofResult, targetListType);
            for (int i = 0; i < listofResult.size(); i++) {
                company = companyService.findComanyByEmployerID(listofJob.get(i).getUserid());
                listofDTO.get(i).setCompanyName(company.getNameCompany());
            }
            for (int i = 0; i < listofResult.size(); i++) {
                city = cityService.getCityById(listofJob.get(i).getCityid());
                listofDTO.get(i).setCityName(city.getFullName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        return listofDTO;


    }

    @Override
    public Job getJobDetail(int id) {
        LOGGER.info("Begin getJobDetail in Job Service with id : " + id);
        Job job = null;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getJobDetail in Job Service with id : " + id);
        return job;
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
