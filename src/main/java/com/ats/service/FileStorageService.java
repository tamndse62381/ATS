package com.ats.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileStorageService {
    void store(MultipartFile file, String storedFilenamme);

    Resource loadFileAsResource(String storedFilename);
}
