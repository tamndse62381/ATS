package com.ats.ws;

import com.ats.dto.JobseekerlikejobDTO;
import com.ats.repository.JobseekerlikejobRespository;
import com.ats.service.JobseekerlikejobService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobseekerlikejob")
public class JobseekerlikejobWS {
    @Autowired
    private JobseekerlikejobService jobseekerlikejobService;

    //create  -- chua test nha
    @PostMapping("/create/{JobSeekerId}/{JobId}")
    @CrossOrigin(origins = "*")
    public RestResponse create(@PathVariable(name = "JobSeekerId")int JobSeekerId,
                                @PathVariable(name = "JobId") int JobId) {
        return jobseekerlikejobService.create(JobSeekerId, JobId);
    }

    //check jobseeker like job or not -- chua test nha
    @RequestMapping(value = "/check/{JobSeekerId}/{JobId}", method = RequestMethod.GET)
    @CrossOrigin("*")
    public boolean check(@PathVariable(name = "JobSeekerId") int JobSeekerId,
                         @PathVariable("JobId") int JobId){

        return true;
    }
}
