package com.ats.repository;

import com.ats.entity.Job;
import com.ats.entity.Jobseekerlikejob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobseekerlikejobRespository extends JpaRepository<Jobseekerlikejob, Integer> {
    Jobseekerlikejob findByUserIdAndJobId(int userid, int jobid);
}
