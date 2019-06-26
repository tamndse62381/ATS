package com.ats.service.impl;

import com.ats.entity.Apply;
import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.ApplyRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean create(int JobSeekerID, int JobID) {
        Users  user = usersRepository.findById(JobSeekerID).orElseThrow(() -> new NotFoundException("Can't found User has ID: " + JobSeekerID));
        Job job = jobRepository.findById(JobID).orElseThrow(() -> new NotFoundException("Can't found Job has ID: " + JobID));
        if (!check(JobSeekerID, JobID))
        {
            Apply apply = new Apply();
            apply.setJobId(JobID);
            apply.setId(JobSeekerID);
            apply.setDayApply(new Timestamp(new Date().getTime()));
            apply.setStatus("1");
            applyRepository.save(apply);
            return true;
        }
        return false;
    }

    @Override
    public boolean check(int JobSeekerID, int JobID) {
        Apply apply = applyRepository.findApplyBy(JobSeekerID, JobID);
        if (apply != null)
            return true;
        return false;
    }
}
