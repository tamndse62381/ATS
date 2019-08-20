package com.ats.service.impl;

import com.ats.dto.FeedBackDTO;
import com.ats.dto.FeedBackDTO2;
import com.ats.entity.Feedback;
import com.ats.entity.Feedbacktype;
import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.CompanyRepository;
import com.ats.repository.FeedBackRepository;
import com.ats.repository.JobRepository;
import com.ats.service.FeedBackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private static final Logger LOGGER = LogManager.getLogger(FeedBackServiceImpl.class);

    @Override
    public int createFeedBack(FeedBackDTO dto) {
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        Feedback feedback;
        int id = -1;
        try {
            ModelMapper mapper = new ModelMapper();
            feedback = mapper.map(dto, Feedback.class);

            Feedbacktype feedbacktype = new Feedbacktype();
            feedbacktype.setId(dto.getFeedBackTypeId());

            Users users = new Users();
            users.setId(dto.getUserId());

            Job job = new Job();
            job.setId(dto.getJobId());

            feedback.setFeedbacktypeByFeedBackTypeId(feedbacktype);
            feedback.setUsersByUserId(users);
            feedback.setJobByJobId(job);


            id = feedBackRepository.save(feedback).getId();
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        return id;
    }

    @Override
    public Page<Feedback> getFeedBackByJobId(int jobid) {
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        return null;
    }

    @Override
    public Page<FeedBackDTO2> getAllFeedBack(Pageable pageable,String search) {
        LOGGER.info("Begin getAllFeedBack in Feedback Service ");
        Page<Feedback> feedbackList = null;
        Page<FeedBackDTO2> feedBackDTO2s = null;
        try {
            feedbackList = feedBackRepository.findAllByCreatedDate(pageable,search);
            feedBackDTO2s = feedbackList.map(new Converter<Feedback, FeedBackDTO2>() {
                @Override
                public FeedBackDTO2 convert(Feedback feedback) {
                    ModelMapper mapper = new ModelMapper();
                    FeedBackDTO2 feedBackDTO2;
                    feedBackDTO2 = mapper.map(feedback, FeedBackDTO2.class);
                    Job job = jobRepository.findOne(feedBackDTO2.getJobId());
                    feedBackDTO2.setCompanyName(job.getCompanyByCompanyId().getNameCompany());
                    feedBackDTO2.setJobName(job.getTitle());
                    return feedBackDTO2;

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllFeedBack in Feedback Service");
        return feedBackDTO2s;
    }
}
