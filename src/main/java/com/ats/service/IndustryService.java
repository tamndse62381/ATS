package com.ats.service;

import com.ats.entity.Industry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndustryService {
    public ResponseEntity<List<Industry>> getAll();
}
