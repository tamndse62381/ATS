package com.ats.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.entity.Industry;
import com.ats.repository.IndustryRepository;

@RestController
@RequestMapping("/industry")
public class IndustryController {
	@Autowired
	private IndustryRepository industryRepository; 
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	@GetMapping("")
	List<Industry> getAll(){
		return industryRepository.findAll();
	}
	
	// get single item
	@GetMapping("/{id}")
	Industry read(@PathVariable int id) {
		try {
			return industryRepository.findOne(id);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		return null;
	}
	
	// Post create item
	@PostMapping("")
	Industry create(@RequestBody Industry newIndustry) {
		try {
			return industryRepository.save(newIndustry);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//Put edit item - đang viết tao lao phải chỉnh lại
	@PutMapping("/{id}")
	Industry update(@RequestBody Industry editedIndustry, @PathVariable int id) {
		Industry industry = industryRepository.findOne(id); 
		if (industry != null) {
			industry.setName(editedIndustry.getName());
			return industryRepository.save(industry);
		} 
		return null;
		
	}
}
