package com.ats.ws;

import com.ats.service.UserlikecvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userlifecv")
public class UserlikecvWS {
    @Autowired
    private UserlikecvService userlikecvService;

    @GetMapping("/check/{EmployerId}/{CvId}")
    @CrossOrigin(origins = "*")
    public boolean check(@PathVariable(name = "EmployerId") int EmployerId,
                         @PathVariable(name = "CVID") int CvId){
        return true;
    }

}
