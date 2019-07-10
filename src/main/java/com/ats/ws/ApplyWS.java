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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse create(@RequestBody Apply newApply){
        return applyService.create(newApply.getJobSeekerId(), newApply.getJobId());
    }

    // edit status when - confirm - deny
    @RequestMapping(value = "/confirm/{applyid}",method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    public RestResponse confirm(@PathVariable(name = "applyid") int applyid){

        return new RestResponse(true, "", null);
    }

    @RequestMapping(value = "/check/{JobSeekerId}/{JobId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "JobSeekerId") int jobseekerid,
                         @PathVariable(name = "JobId") int jobid){
        return applyService.check(jobseekerid, jobid);
    }
}
