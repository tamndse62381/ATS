package com.ats.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.dto.CompanyDTO;
import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import com.ats.transformer.CompanyTransformer;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CompanyTransformer companyTransformer;
	
	@Override
	public List<CompanyDTO> getAll() {
		List<Company> list = companyRepository.findAll();
		List<CompanyDTO> listDTO = new ArrayList<CompanyDTO>();
		if (list != null) {
			for (Company company : list) {
				listDTO.add(companyTransformer.convertEntityToDTO(company)); 
			}
		}
		return listDTO;
	}
}