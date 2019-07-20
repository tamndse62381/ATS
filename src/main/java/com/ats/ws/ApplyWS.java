package com.ats.ws;

import com.ats.entity.Apply;
import com.ats.service.ApplyService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.Path;

@RestController
@RequestMapping("/apply")
public class ApplyWS {
    @Autowired
    private ApplyService applyService;

    // create when jobseeker apply a job
    @RequestMapping(value = "/create/{CvId}/{JobId}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse create(@PathVariable(name = "CvId") int CvId,
                               @PathVariable(name = "JobId") int JobId){
        return applyService.create(CvId, JobId);
    }

    // edit status - confirm
    @RequestMapping(value = "/confirm/{jobid}/{cvid}",method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse confirm(@PathVariable(name = "jobid") int jobid,
                                @PathVariable(name = "cvid") int cvid){
        return applyService.confirm(jobid, cvid);
    }

    // edit status - deny
    @RequestMapping(value = "/deny/{jobid}/{cvid}", method = RequestMethod.GET)
    @CrossOrigin("*")
    public RestResponse deny(@PathVariable(name = "jobid") int jobid,
                             @PathVariable(name = "cvid") int cvid){
        return applyService.deny(jobid, cvid);
    }

    // check
    @RequestMapping(value = "/check/{JobSeekerId}/{JobId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "JobSeekerId") int jobseekerid,
                         @PathVariable(name = "JobId") int jobid){
        return applyService.check(jobseekerid, jobid);
    }

    // get list Apply cua mot job nao do
    @RequestMapping(value = "/list-applied/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse getListJobAppliedByJobId(@PathVariable(name = "id") int JobSeekerId){
        return applyService.listJob(JobSeekerId);
    }


    // get List Cv applied for one Job
        @GetMapping("/cv-applied/{jobid}")
    @CrossOrigin(origins = "*")
    public RestResponse getListCvAppliedForOneJob(@PathVariable(name = "jobid") int JobId){
        return applyService.listCv(JobId);
    }
}
