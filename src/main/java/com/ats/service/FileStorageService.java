package com.ats.service;

import com.ats.util.RestResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.io.File;

@Service
public interface FileStorageService {
    void store(MultipartFile file, String storedFilename);

    RestResponse loadFileAsResource(String storedFilename);

    Resource loadImg(String storedFilename);
}
