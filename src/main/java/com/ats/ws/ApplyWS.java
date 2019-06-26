package com.ats.ws;

import com.ats.entity.Apply;
import com.ats.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
public class ApplyWS {
    @Autowired
    private ApplyService applyService;

    // create when jobseeker apply a job
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<Apply> create(@RequestBody Apply newApply, BindingResult result){
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(null);
        boolean check = applyService.create(newApply.getJobseekerid(), newApply.getJobid());
        if (check)
            return ResponseEntity.ok(newApply);
        return ResponseEntity.badRequest().body(null);
    }

    // edit status when - confirm - deny
    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<Apply> confirm(){

        return ResponseEntity.badRequest().body(null);
    }


}
