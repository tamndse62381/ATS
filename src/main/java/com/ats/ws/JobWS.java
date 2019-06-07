package com.ats.ws;

import com.ats.entity.Job;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ats.util.RestResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/job")
public interface JobWS {

    @ResponseBody
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE})
    RestResponse createJob(@Valid @RequestBody Job job);
}
