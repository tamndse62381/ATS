package com.ats.ws;

import com.ats.entity.Cv;
import com.ats.entity.Job;
import com.ats.repository.CVRepository;
import com.ats.repository.CompanyRepository;
import com.ats.repository.JobRepository;
import com.ats.service.CVService;
import com.ats.service.FileStorageService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestWS {
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void create(MultipartFile file){
        fileStorageService.store(file, file.getOriginalFilename());
    }

    @GetMapping("/get")
    public List<Cv> get (){
    return cvRepository.findAll();
    }

    @GetMapping("/getJob")
    public List<Job> getJob(){
        return jobRepository.findAll();
    }

    @GetMapping("/getCompany")
    public RestResponse getCompay(){
        return new RestResponse(true, "Thành công!!!!", companyRepository.findAll());
    }
}

