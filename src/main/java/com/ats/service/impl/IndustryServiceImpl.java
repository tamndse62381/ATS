package com.ats.service.impl;

import com.ats.entity.Industry;
import com.ats.repository.IndustryRepository;
import com.ats.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public ResponseEntity<List<Industry>> getAll() {
        try {
            return ResponseEntity.ok().body(industryRepository.findAll());
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
