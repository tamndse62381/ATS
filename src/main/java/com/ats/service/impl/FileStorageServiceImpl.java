package com.ats.service.impl;


import com.ats.exception.MyFileNotFoundException;
import com.ats.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl() {
        this.fileStorageLocation = Paths.get("src/main/resources/static/uploads");
    }

    @Override
    public void store(MultipartFile file, String storedFilename) {
        try {
            // Check if the file's name contains invalid characters
            if (storedFilename.contains("..")) {
                throw new BadRequestException("Sorry! Filename contains invalid path sequence " + storedFilename);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(storedFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new BadRequestException("Could not store file " + storedFilename + ". Please try again!");
        }
    }

    @Override
    public Resource loadFileAsResource(String storedFilename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(storedFilename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + storedFilename);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + storedFilename);
        }
    }
}
