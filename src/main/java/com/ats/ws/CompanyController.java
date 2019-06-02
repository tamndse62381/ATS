package com.ats.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyRepository companyRepository; 
	
	@RequestMapping("")
	List<Company> getAll(){
		return companyRepository.findAll();
	}
}
