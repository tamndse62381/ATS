package com.ats.service.impl;

import com.ats.entity.Countjob;
import com.ats.entity.Job;
import com.ats.entity.Users;
import com.ats.repository.CountjobRepository;
import com.ats.repository.JobRepository;
import com.ats.repository.UsersRepository;
import com.ats.service.CountjobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CountjobServiceImpl implements CountjobService{
    @Autowired
    private CountjobRepository countjobRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public void countWhenEmployerGetDetailOfJob(int JobID, int JodSeekerID) {
        Job job = jobRepository.findById(JobID).orElseThrow(()->
                new NotFoundException("Can't found Job have ID: " + JobID));
        Users user = usersRepository.findById(JodSeekerID).orElseThrow(() ->
                new NotFoundException("Can't found JobSeeker havr ID: " + JodSeekerID));
        Countjob count = countjobRepository.findCountcv(JobID, JodSeekerID);
        if (count != null) {
            count.setJobId(JobID);
            count.setUserId(JodSeekerID);
            count.setCreatedDate(new Timestamp(new Date().getTime()));
            countjobRepository.save(count);
        }
    }

    @Override
    public int countAccessTimes(int EmployerId) {
        List<Job> listCv = jobRepository.findByUserId(EmployerId);
        int count = 0;
        for (Job job : listCv) {
            count =+ countjobRepository.countCountjobByUserId(job.getId());
            return count;
        }
        return 0;
    }
}
