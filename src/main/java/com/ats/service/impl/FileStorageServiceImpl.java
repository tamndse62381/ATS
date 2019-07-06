package com.ats.service.impl;

import com.ats.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private Path fileStoreLocation;

    @Autowired
    public FileStorageServiceImpl(){
        this.fileStoreLocation = Paths.get("src/main/resources/static/uploads");
    }

    @Override
    public void store(MultipartFile file, String storedFilename) {
        try {
            if (storedFilename.contains("..")) {
//                return "Sorry!!! File name contains invalid path sequence" + storedFilename;
            }
            Path targetLocation = this.fileStoreLocation.resolve(storedFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
//            return "Couldn't store file " + storedFilename + ". Please try again!!!";
        }
    }
}
