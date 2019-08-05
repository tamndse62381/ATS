package com.ats.service.impl;

import com.ats.dto.JobDTO2;
import com.ats.dto.JobDTO;
import com.ats.dto.JobDTO3;
import com.ats.entity.*;
import com.ats.repository.*;
import com.ats.service.*;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    CVRepository cvRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CountjobService countjobService;
    @Autowired
    ApplyService applyService;
    @Autowired
    ApplyRepository applyRepository;

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
            newJob.setTitle(job.getTitle());
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
    public Page<JobDTO> searchJob(String job, String city, String industry, Pageable pageable) {
        LOGGER.info("Begin searchJob in Job Service with job name : {} ", job);
        Page<Job> pageOfJob;
        Page<JobDTO> pageDTO = null;
        try {
            LOGGER.info("Begin searchJob in Job Repository with job name : {} ", job);
            pageOfJob = jobRepository.searchJob(job, pageable, "approved", new Date(), city, industry);

            ModelMapper mapper = new ModelMapper();
            pageDTO = pageOfJob.map(new Converter<Job, JobDTO>() {
                @Override
                public JobDTO convert(Job job) {
                    JobDTO dto;
                    dto = mapper.map(job, JobDTO.class);
                    return dto;
                }
            });

            LOGGER.info("End searchJob in Job Repository with job list size : {} ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End searchJob in Job Service with job list size : {} ");
        return pageDTO;
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
                    listofResult.add(listofJob.get(i).getTitle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getALlJobTitle in Job Service");
        return listofResult;
    }

    @Override
    public Page<JobDTO> getTop8(Pageable pageable) {
        LOGGER.info("Begin getTop8 in Job Service");
        Page<Job> listofJob = null;
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            LOGGER.info("Begin getTop8 in Job Repository ");
            listofJob = jobRepository.getTop8(pageable, "approved", new Date());
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob.getContent(), targetListType);
            pageDTO = new PageImpl<>(listofDTO, new PageRequest(listofJob.getTotalPages(), listofJob.getSize()
                    , listofJob.getSort()), listofDTO.size());
            LOGGER.info("End getTop8 in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        return pageDTO;
    }

    @Override
    public List<JobDTO> getTop8Mobile() {
        List<Job> listofJob = null;
        List<JobDTO> listofDTO = null;
        try {
            LOGGER.info("Begin getTop8 in Job Repository ");
            listofJob = jobRepository.getTop8Mobile("approved", new Date());
            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(listofJob, targetListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getTop8 in Job Service");
        return listofDTO;
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
    public JobDTO3 getJobDetail(int id, int userId) {
        LOGGER.info("Begin getJobDetail in Job Service with id : " + id);
        Job job;
        JobDTO3 jobDTO = null;

        try {
            LOGGER.info("Begin getJobDetail in Job Repository with id : " + id);
            job = jobRepository.findOne(id);
            countjobService.countWhenEmployerGetDetailOfJob(id, userId);
            LOGGER.info("End getJobDetail in Job Repository with id : " + id);
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
        LOGGER.info("Begin changeStatus in Job Service with Job id - newStatus : {}", id + newStatus);
        int success;
        success = jobRepository.changeStatus(id, newStatus);
        Job job = jobRepository.findOne(id);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 30);
        job.setEndDateForApply(new Timestamp(c.getTimeInMillis()));
        job.setStatus(newStatus);
        jobRepository.save(job);
        LOGGER.info("End changeStatus in Job Service with result: {}", success);
        return success;
    }

    @Override
    public Page<JobDTO> suggestJob(int cvId, Pageable pageable) {
        LOGGER.info("Begin suggestJob in Job Service with cvId : " + cvId);
        List<Integer> skillInCvId = new ArrayList();
        List<String> skillInCvName = new ArrayList<>();
        List<Job> jobList;

        List<Job> suggestJobList = new ArrayList<>();
        Page<Job> jobPage;

        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            Cv cv = cvRepository.findOne(cvId);
            System.out.println("SKILL của CV Ở ĐÂY");
            for (int i = 0; i < cv.getSkillincvsById().size(); i++) {
                skillInCvName.add(cv.getSkillincvsById().get(i).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
                skillInCvId.add(cv.getSkillincvsById().get(i).getSkillId());
                System.out.println(cv.getSkillincvsById().get(i).getSkillId());
                System.out.println(cv.getSkillincvsById().get(i).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
            }
            Date date = new Date();

            String cityName = cv.getCityByCityId().getFullName();
            String industryName = cv.getIndustryByIndustryId().getName();

            jobPage = jobRepository.suggestJob(cv.getYearExperience(), industryName,
                    cityName, "approved", date, pageable);
            jobList = jobPage.getContent();

            System.out.println("CÓ NHA : " + jobList.size());
            // Get All Job Check All Field
            System.out.println("Get All Job Check All Field");
            for (int i = 0; i < jobList.size(); i++) {
                for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                    System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                    if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                        if (!suggestJobList.contains(jobList.get(i))) {
                            suggestJobList.add(jobList.get(i));
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        cityName, "approved", date, pageable);
                jobList = jobPage.getContent();

                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience
                System.out.println(" Get All Job Without Year Experience");
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience, cityName
                System.out.println(" Get All Job Without Year Experience, cityName");
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience,cityName,industryName
                System.out.println(" Get All Job Without Year Experience, cityName , industryName");
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            //Bỏ hết Field Check Skill Name
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvName.contains(jobList.get(i).getSkillneedforjobsById().get(j).
                                getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            System.out.println("SIZE ở đây : " + suggestJobList.size());

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(suggestJobList, targetListType);

            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End suggestJob in Job Service with cvId : " + cvId);
        return pageDTO;
    }

    @Override
    public Page<JobDTO> suggestJobByJobId(int jobid, Pageable pageable) {
        LOGGER.info("Begin suggestJob in Job Service with jobid : " + jobid);
        List<Integer> skillInCvId = new ArrayList();
        List<String> skillInCvName = new ArrayList<>();
        List<Job> jobList;

        List<Job> suggestJobList = new ArrayList<>();
        Page<Job> jobPage;

        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            Job job = jobRepository.findOne(jobid);
            System.out.println("SKILL của Job Ở ĐÂY");
            for (int i = 0; i < job.getSkillneedforjobsById().size(); i++) {
                skillInCvName.add(job.getSkillneedforjobsById().get(i).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
                skillInCvId.add(job.getSkillneedforjobsById().get(i).getSkillId());
                System.out.println(job.getSkillneedforjobsById().get(i).getSkillId());
                System.out.println(job.getSkillneedforjobsById().get(i).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
            }
            Date date = new Date();

            String cityName = job.getCityByCityId().getFullName();
            String industryName = job.getIndustryByIndustryId().getName();

            jobPage = jobRepository.suggestJob(100, industryName,
                    cityName, "approved", date, pageable);
            jobList = jobPage.getContent();

            System.out.println("CÓ NHA : " + jobList.size());
            // Get All Job Check All Field
            System.out.println("Get All Job Check All Field");
            for (int i = 0; i < jobList.size(); i++) {
                for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                    if (jobList.get(i).getId() != jobid) {
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        cityName, "approved", date, pageable);
                jobList = jobPage.getContent();

                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience
                System.out.println(" Get All Job Without Year Experience");
                for (int i = 0; i < jobList.size(); i++) {
                    if (jobList.get(i).getId() != jobid) {
                        for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                            System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                            if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                                if (!suggestJobList.contains(jobList.get(i))) {
                                    suggestJobList.add(jobList.get(i));
                                }
                            }
                        }
                    }
                }
            }

            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience, cityName
                System.out.println(" Get All Job Without Year Experience, cityName");
                for (int i = 0; i < jobList.size(); i++) {
                    if (jobList.get(i).getId() != jobid) {
                        for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                            System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                            if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                                if (!suggestJobList.contains(jobList.get(i))) {
                                    suggestJobList.add(jobList.get(i));
                                }
                            }
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience, cityName
                System.out.println(" Get All Job Without Year Experience, cityName");
                for (int i = 0; i < jobList.size(); i++) {
                    if (jobList.get(i).getId() != jobid) {
                        for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                            if (skillInCvName.contains(jobList.get(i).getSkillneedforjobsById().get(j).
                                    getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName())) {
                                if (!suggestJobList.contains(jobList.get(i))) {
                                    suggestJobList.add(jobList.get(i));
                                }
                            }
                        }
                    }

                }
            }


            System.out.println("SIZE ở đây : " + suggestJobList.size());

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(suggestJobList, targetListType);

            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End suggestJob in Job Service with jobid : " + jobid);
        return pageDTO;
    }

    @Override
    public Page<JobDTO> suggestJobByUserId(int userid, Pageable pageable) {
        LOGGER.info("Begin suggestJob in Job Service with userid : " + userid);
        List<Integer> skillInCvId = new ArrayList();
        List<String> skillInCvName = new ArrayList<>();
        List<Job> jobList;

        List<Job> suggestJobList = new ArrayList<>();
        Page<Job> jobPage;
        Cv cv = null;
        List<JobDTO> listofDTO;
        Page<JobDTO> pageDTO = null;
        try {
            Users users = usersRepository.findOne(userid);
            for (int i = 0; i < users.getCvsById().size(); i++) {
                if (users.getCvsById().get(i).getIsActive() == 1) {
                    cv = cvRepository.findOne(users.getCvsById().get(i).getId());
                }
            }
            List<Apply> applies = applyRepository.findAppliesByCvid(cv.getId());
            List<Job> jobs = new ArrayList<>();
            for (int i = 0; i < applies.size(); i++) {
                jobs.add(applies.get(i).getJobByJobId());
            }
            System.out.println("SKILL của CV Ở ĐÂY");
            for (int i = 0; i < cv.getSkillincvsById().size(); i++) {
                skillInCvName.add(cv.getSkillincvsById().get(i).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
                skillInCvId.add(cv.getSkillincvsById().get(i).getSkillId());
                System.out.println(cv.getSkillincvsById().get(i).getSkillId());
                System.out.println(cv.getSkillincvsById().get(i).getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName());
            }
            Date date = new Date();

            String cityName = cv.getCityByCityId().getFullName();
            String industryName = cv.getIndustryByIndustryId().getName();

            jobPage = jobRepository.suggestJob(cv.getYearExperience(), industryName,
                    cityName, "approved", date, pageable);
            jobList = jobPage.getContent();

            System.out.println("CÓ NHA : " + jobList.size());
            // Get All Job Check All Field
            System.out.println("Get All Job Check All Field");
            for (int i = 0; i < jobList.size(); i++) {
                for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                    System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                    if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                        if (!suggestJobList.contains(jobList.get(i))) {
                            suggestJobList.add(jobList.get(i));
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        cityName, "approved", date, pageable);
                jobList = jobPage.getContent();

                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience
                System.out.println(" Get All Job Without Year Experience");
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, industryName,
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience, cityName
                System.out.println(" Get All Job Without Year Experience, cityName");
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                System.out.println("CÓ NHA : " + jobList.size());
                // Get All Job Without Year Experience,cityName,industryName
                System.out.println(" Get All Job Without Year Experience, cityName , industryName");
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvId.contains(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            //Bỏ hết Field Check Skill Name
            if (suggestJobList.size() < 4) {
                jobPage = jobRepository.suggestJob(100, "",
                        "", "approved", date, pageable);
                jobList = jobPage.getContent();
                for (int i = 0; i < jobList.size(); i++) {
                    for (int j = 0; j < jobList.get(i).getSkillneedforjobsById().size(); j++) {
                        System.out.println(jobList.get(i).getSkillneedforjobsById().get(j).getSkillId());
                        if (skillInCvName.contains(jobList.get(i).getSkillneedforjobsById().get(j).
                                getSkillBySkillId().getSkillmasterBySkillMasterId().getSkillName())) {
                            if (!suggestJobList.contains(jobList.get(i))) {
                                suggestJobList.add(jobList.get(i));
                            }
                        }
                    }
                }
            }
            System.out.println("SIZE ở đây : " + suggestJobList.size());
            System.out.println("đã apply : " + jobs.size());
            for (int i = 0; i < jobs.size(); i++) {
                if (suggestJobList.contains(jobs.get(i))) {
                    suggestJobList.remove(jobs.get(i));
                }
            }


            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<JobDTO>>() {
            }.getType();
            listofDTO = mapper.map(suggestJobList, targetListType);

            pageDTO = new PageImpl<>(listofDTO, new PageRequest(0, 10), listofDTO.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End suggestJob in Job Service with userid : " + userid);
        return pageDTO;
    }

    public RestResponse findListJobValid(int EmployerId) {
        List<Job> listJob = jobRepository.getJobValid(EmployerId, new Timestamp(new Date().getTime()));
        if (listJob == null)
            return new RestResponse(false, "không có công việc nào!!!", null);
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    @Override
    public RestResponse findListJobInValid(int EmployerId) {
        List<Job> listJob = jobRepository.getJobInValid(EmployerId, new Timestamp(new Date().getTime()));
        if (listJob == null)
            return new RestResponse(false, "Không có công việc nào!!", null);
        return new RestResponse(true, "Thành công!!!", listJob);
    }

    public Page<Job> getAllJob(Pageable pageable, String search, String status) {
        LOGGER.info("Begin getAllJob in Job Service");
        Page<Job> listofJob = null;
        try {
            LOGGER.info("Begin getAllJob in Job Repository ");
            listofJob = jobRepository.getAll(pageable, search, status);
            LOGGER.info("End getAllJob in Job Repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllJob in Job Service");
        return listofJob;
    }

}
