package com.ats.service.impl;

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
import java.util.ArrayList;
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
    public int createJob(Job job) {
        LOGGER.info("Begin createJob in Job Service with job name : {}", job.getTitle());
        int result = 0;
        Job newJob;
        try {
            newJob = jobRepository.save(job);
            result = newJob.getID();
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
        List<JobDTO> listofDTO = new ArrayList<>();
        try {
            LOGGER.info("Begin searchJob in Job Repository with job name : {} ", job);
            listofJob = jobRepository.searchJob(job);
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob, targetListType);
            LOGGER.info("End searchJob in Job Repository with job list size : {} ", listofJob.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in Job Service with job list size : {} ", listofJob.size());
        return listofDTO;
    }

    @Override
    public List<JobDTO> getTop8() {
        LOGGER.info("Begin getTop8 in Job Service with job name : {}");
        List<Job> listofJob;
        Company company;
        City city;
        List<JobDTO> listofDTO = new ArrayList<>();
//        Pageable firstPageWithTwoElements = new PageRequest.of(0,2);
        try {
            LOGGER.info("Begin getTop8 in Job Repository with job name : {}");
            listofJob = jobRepository.getTop8();
            ModelMapper mapper = new ModelMapper();
            Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob, targetListType);
            for (int i = 0; i < listofJob.size(); i++) {
                company = companyService.findComanyByEmployerID(listofJob.get(i).getUserid());
                listofDTO.get(i).setCompanyName(company.getNameCompany());
            }
            for (int i = 0; i < listofJob.size(); i++) {
                city = cityService.getCityById(listofJob.get(i).getCityid());
                listofDTO.get(i).setCityName(city.getFullName());
            }
            LOGGER.info("End getTop8 in Job Repository with job name : {}");


        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service with job name : {}");
        return listofDTO;


    }

}
