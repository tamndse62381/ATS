package com.ats.ws;

import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.dto.CompanyDTO;
import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	private static final Logger LOGGER = LogManager.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyRepository companyRepository; 
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/")
	List<CompanyDTO> getAll(){
		return companyService.getAll();
		
	}
	
	// find a Company by Name
	@RequestMapping(value = "/getByName", method = RequestMethod.GET)
	List<Company> getByName(@RequestParam("name") String name){
		try {
			return companyRepository.findByName(name);
		} catch (RuntimeException e) {
			LOGGER.info("get By Name, Exception: " + e);
		}
		return null;
	}
	
	// find a Company by Id 
	@GetMapping("get_by_id/{id}")
	Company getById(@PathVariable int id) {
		try {
			return companyRepository.findOne(id);
		} catch (RuntimeException e) {
			LOGGER.info("get By Id, Exception: " + e);
		}
		return null;
	}
	
	//create a new company 
	@PostMapping("")
	Company create(@RequestBody Company newCompany) {
		newCompany.setCreatedDate(Calendar.getInstance().getTime());
		newCompany.setLastModify(Calendar.getInstance().getTime());
		return companyRepository.save(newCompany);
	}
	
	// edit company 
	@PutMapping("")
	Company update(@RequestBody Company editedCompany, @PathVariable int id) {
		try {
			Company company = companyRepository.findOne(id); 
			if (company != null) {
				editedCompany.setLastModify(Calendar.getInstance().getTime());
				return companyRepository.save(editedCompany);
			}
		} catch (Exception e) {
			LOGGER.info("Edit Company, Exception: " + e);
		}
		return null;
	}
	
	// deleted a company - change status to deleted
	@PutMapping("/delete_company")
	Company delete(@RequestBody Company editedCompany, @PathVariable int id) {
		try {
			Company company = companyRepository.findOne(id); 
			if (company != null) {
				editedCompany.setLastModify(Calendar.getInstance().getTime());
				// editedCompany.set - chỗ này change status nè mà DB chưa có nha 
				return companyRepository.save(editedCompany);
			}
		} catch (Exception e) {
			LOGGER.info("Edit Company, Exception: " + e);
		}
		return null;
	}
}
