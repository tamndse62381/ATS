package com.ats.ws;

import com.ats.entity.Workexperience;
import com.ats.service.WorkexperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/worlexperience")
public class WorkexperienceWS {
    @Autowired
    private WorkexperienceService workexperienceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin (origins = "")
    public ResponseEntity<List<Workexperience>> getAll(){
        return ResponseEntity.ok().body(workexperienceService.getAll());
    }
}
