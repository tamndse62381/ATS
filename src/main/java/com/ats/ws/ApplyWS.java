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
    @RequestMapping(value = "/confirm/{applyid}",method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse confirm(@PathVariable(name = "applyid") int applyid){
        return applyService.confirm(applyid);
    }

    // edit status - deny
    @RequestMapping(value = "/deny//{applyid}", method = RequestMethod.GET)
    @CrossOrigin("*")
    public RestResponse deny(@PathVariable(name = "applyid") int applyid){
        return applyService.deny(applyid);
    }

    // check
    @RequestMapping(value = "/check/{JobSeekerId}/{JobId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "JobSeekerId") int jobseekerid,
                         @PathVariable(name = "JobId") int jobid){
        return applyService.check(jobseekerid, jobid);
    }

    // get list Apply cua mot job nao do
    @RequestMapping(value = "/get-list/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public RestResponse getListApplyByJobId(@PathVariable int JobId){

        return new RestResponse(true, "Thành công!!!", null);
    }
}
