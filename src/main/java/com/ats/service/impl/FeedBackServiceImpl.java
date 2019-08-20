package com.ats.service.impl;

import com.ats.dto.FeedBackDTO;
import com.ats.entity.Feedback;
import com.ats.entity.Feedbacktype;
import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.FeedBackRepository;
import com.ats.service.FeedBackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackRepository feedBackRepository;

    private static final Logger LOGGER = LogManager.getLogger(FeedBackServiceImpl.class);

    @Override
    public int createFeedBack(FeedBackDTO dto) {
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        Feedback feedback;
        int id = -1;
        try {
            ModelMapper mapper = new ModelMapper();
            feedback = mapper.map(dto,Feedback.class);

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
    public Page<Feedback> getAllFeedBack(Pageable pageable) {
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin createJob in Job Service with job name : {}");
        return null;
    }
}
