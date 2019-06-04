package com.ats.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ats.dto.CompanyDTO;

@Service
public interface CompanyService {
	 List<CompanyDTO> getAll();
}
