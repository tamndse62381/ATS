package com.ats.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{	
//	@Query("Select j from Job j where j.enddateforapply >= :currentTime")
//	public List<Job> findAccountByEmail(@Param("currentTime") Date currentTime);
}
