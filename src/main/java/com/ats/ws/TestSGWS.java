package com.ats.ws;

import com.ats.service.FileStorageService;
import com.ats.service.TestCVService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/testSG")
public class TestSGWS {
    @Autowired
    private TestCVService testCVService;

    @GetMapping("/get")
    @CrossOrigin(origins = "*")
    public RestResponse listJobsByEmployerId() {
        return (RestResponse) testCVService.suggest();
    }
//    @GetMapping("/get")
//    public Resource get(){
//        return  fileStorageService.loadImg("123.PNG");
//    }
}
