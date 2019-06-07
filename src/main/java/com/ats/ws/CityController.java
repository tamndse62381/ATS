package com.ats.ws;

import com.ats.entity.City;
import com.ats.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public ResponseEntity<City> getAllOne(){
//        return ResponseEntity.ok().body(null);
//    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAll(){
        try {
            return ResponseEntity.ok(cityRepository.findAll());
        } catch(RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // create a new City - admin lam
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<City> create(@RequestBody City newCity){
        cityRepository.save(newCity);
        return ResponseEntity.ok().body(null);
    }

    // put edit item
    @PutMapping("/{id}")
    ResponseEntity<City> update(@RequestBody City editedCity, @PathVariable int id){
        if (cityRepository.findOne(id) != null){
            // cho nay chua biet viet gi
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Delete A city
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        cityRepository.delete(id);
    }
}
