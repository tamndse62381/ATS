package com.ats.ws;

import com.ats.service.CountcvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countcv")
public class CountcvWS {
    @Autowired
    private CountcvService countcvService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public int countCv(@PathVariable int id){
        return countcvService.countAccessTimes(id);
    }
}
