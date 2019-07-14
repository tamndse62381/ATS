package com.ats.ws;

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
        return jobseekerlikejobService.check(JobSeekerId, JobId);
    }

    // list các Job ma một JobSeeker da like
    @RequestMapping(value = "/list/{JobSeekerId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse listJob(@PathVariable int JobSeekerId){
        return jobseekerlikejobService.listJob(JobSeekerId);
    }

    // un-check = delete
    @PostMapping("uncheck/{JobSeekerId}/{JobId}")
    @CrossOrigin("*")
    public RestResponse unCheck(@PathVariable(name = "JobSeekerId") int JobSeekerId,
                           @PathVariable("JobId") int JobId){
        return jobseekerlikejobService.unCheck(JobSeekerId, JobId);
    }
}
