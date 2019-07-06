package com.ats.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileStorageService {
    void store(MultipartFile file, String storedFilename);
}
