package com.ats.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.entity.City;
import com.ats.repository.CityRepository;

@RestController
@RequestMapping("/city")
public class CityController {
	@Autowired
	private CityRepository cityRepository; 
	
	@GetMapping("/get_all")
	List<City> getAll(){
		return cityRepository.findAll();
	}
	
}
