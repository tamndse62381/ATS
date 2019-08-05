package com.ats.ws;

import com.ats.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/test")
public class TestWS {
    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void create(MultipartFile file){
        fileStorageService.store(file, file.getOriginalFilename());
    }

//    @GetMapping("/get")
//    public Resource get(){
//        return  fileStorageService.loadImg("123.PNG");
//    }
}
