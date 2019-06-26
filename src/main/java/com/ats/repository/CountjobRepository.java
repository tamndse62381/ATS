package com.ats.repository;

import com.ats.entity.Countjob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountjobRepository extends JpaRepository<Countjob, Integer> {
    @Query("Select c from Countjob c where c.jobid = :jobid and c.userid = :userid")
    Countjob findCountcv (@Param("jobid") int jobid, @Param("userid") int userid);

    int countCountjobByUserid(int id);
}
