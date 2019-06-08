package com.ats.ws;

import com.ats.entity.Education;
import com.ats.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationWS {
    @Autowired
    private EducationRepository educationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Education>> getByCVID(@RequestParam("cvid") int id){
        try {
            // find by cai cvid de lay ra
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }




}
