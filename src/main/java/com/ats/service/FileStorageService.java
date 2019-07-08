package com.ats.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.File;

@Service
public interface FileStorageService {
    void store(MultipartFile file, String storedFilename);

    Resource loadFileAsResource(String storedFilename);

    File loadImg(String storedFilename);
}
