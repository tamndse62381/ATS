package com.ats.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.entity.Company;
import com.ats.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{	
//	@Query("SELECT j from Job j where j.endateforapply >= :currentTime")
//    public List<Job> findAvailable(@Param("currentTime") Date currentTime);
	@Query(value="Select j from Job j where j.endDateForApply >= :currentTime", nativeQuery = false)
	public List<Job> findByAvailable(@Param("currentTime") Date currentTime);
}
