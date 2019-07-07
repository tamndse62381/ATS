package com.ats.service.impl;

import com.ats.service.FileStorageService;
import com.ats.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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

    @Override
    public RestResponse loadFileAsResource(String storedFilename) {
        try {
            Path filePath = this.fileStoreLocation.resolve(storedFilename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return new RestResponse(true, "", resource);
            } else {
                return new RestResponse(false, "Không tìm thấy!!!", null);
            }
        } catch (MalformedURLException ex) {
            return new RestResponse(false, "Không tìm thấy!!!", null);
        }
    }
}
