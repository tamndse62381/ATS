package com.ats.repository;

import com.ats.entity.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuggestRepository extends JpaRepository<Suggest,Integer> {
    @Query("Select a from Suggest a where a.jobid = :jobid")
    List<Suggest> getListCVByJobid(@Param("jobid") int jobid);
    @Query("DELETE FROM Suggest a WHERE a.jobid = :jobid")
    Void deleteSuggestByJobID(@Param("jobid") int jobid);

}
