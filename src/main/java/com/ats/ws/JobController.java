package com.ats.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.entity.Job;
import com.ats.repository.JobRepository;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobRepository jobRepository;
	
	@RequestMapping("")
	List<Job> getAll(){
		return jobRepository.findAll();
	}
}
