package com.ats.ws;

import javax.ws.rs.POST;

import com.ats.entity.Job;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.util.RestResponse;

@RestController
@RequestMapping("/job")
public interface JobWS {

	
	@POST
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/create", produces = "application/json;charset=UTF-8")
	RestResponse createJob(@RequestBody Job job);
}
