package com.ats.ws;

import com.ats.entity.Job;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ats.util.RestResponse;

import javax.ws.rs.POST;

@RestController
@RequestMapping("/job")
public interface JobWS {


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE})
    RestResponse createJob(@RequestBody Job job);
}
