package com.ats.ws;

import com.ats.service.CountjobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countjob")
public class CountjobWS {
    @Autowired
    private CountjobService countjobService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public int countJob(@PathVariable int id){
        return 0;
    }
}
