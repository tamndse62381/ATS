package com.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{
	
}
