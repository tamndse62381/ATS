package com.ats.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.entity.City;
import com.ats.repository.CityRepository;

@RestController
@RequestMapping("/city")
public class CityController {
	
	private CityRepository cityRepository; 
	
	@Autowired
	public CityController(CityRepository cityRepository) {
		super();
		this.cityRepository = cityRepository;
	}

//	
//	@GetMapping("/get_all")
//	List<City> getAll(){
//		return cityRepository.findAll();
//	}
	
	@GetMapping()
	public ResponseEntity<Page<City>> getAll(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(cityRepository.findAll(pageable));
	}
}
