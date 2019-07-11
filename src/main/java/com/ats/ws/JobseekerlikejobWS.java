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

    //create
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public RestResponse create(@RequestBody JobseekerlikejobDTO newJobseekerlikejobDTO){
        return new RestResponse(true, "Thành công!!!", null);
    }

    //check jobseeker like job or not
    @RequestMapping(value = "/check/{JobSeekerId}/{JobId}", method = RequestMethod.GET)
    @CrossOrigin("*")
    public boolean check(@PathVariable(name = "JobSeekerId") int JobSeekerId,
                         @PathVariable("JobId") int JobId){

        return true;
    }
}
