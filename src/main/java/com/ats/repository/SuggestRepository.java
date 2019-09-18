package com.ats.repository;

import com.ats.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestRepository extends JpaRepository<Suggest,Integer> {
    @Query("Select a from Suggest a where a.jobid = :jobid")
    Suggest getCVByJobid( @Param("jobid") int jobid);
}
