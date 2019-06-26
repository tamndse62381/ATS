package com.ats.repository;

import com.ats.entity.Skillneedforjob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillNeedForJobRepository extends JpaRepository<Skillneedforjob, Integer> {
    @Query("Select s from Skillneedforjob s where s.jobId = :jobid and s.skillId = :skillid")
    Skillneedforjob findSkillneedforjob(@Param("jobid") int jobid, @Param("skillid") int skillid);
}
