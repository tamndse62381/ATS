package com.ats.ws;

import com.ats.entity.Cv;
import com.ats.repository.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVController {
    @Autowired
    private CVRepository cvRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cv>> getAll(){
        try{
            return ResponseEntity.ok().body(cvRepository.findAll());
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
