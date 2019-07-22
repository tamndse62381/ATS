package com.ats.service;

import com.ats.entity.Servicefunction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceFunctionService {
    List<Servicefunction> getAllService();
}
