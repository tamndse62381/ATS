package com.ats.ws;

import com.ats.entity.Industry;
import com.ats.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
public class IndustryWS {
    @Autowired
    private IndustryRepository industryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Industry>> getAll(){
        try {
            return ResponseEntity.ok().body(industryRepository.findAll());
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public ResponseEntity<Industry> getById(@RequestParam("id") int id){
        try {
            return ResponseEntity.ok().body(industryRepository.findOne(id));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // create new Industry
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Industry> create(@RequestBody Industry newIndustry){
        industryRepository.save(newIndustry);
        return ResponseEntity.ok().body(null);
    }

    // put edit item
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Industry> update(@RequestBody Industry editedIndustry) {
        if (industryRepository.findOne(editedIndustry.getId()) != null) {
            return ResponseEntity.ok().body(industryRepository.save(editedIndustry));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
