package com.ats.ws;

import com.ats.entity.Industry;
import com.ats.repository.IndustryRepository;
import com.ats.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
public class IndustryWS {
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private IndustryService industryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Industry>> getAll(){
        return industryService.getAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public ResponseEntity<Industry> getById(@RequestParam("id") int id){
        try {
            return ResponseEntity.ok().body(industryRepository.getOne(id));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // create new Industry
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Industry> create(@RequestBody Industry newIndustry){
        industryRepository.save(newIndustry);
        return ResponseEntity.ok().body(null);
    }

    // put edit item
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Industry> update(@RequestBody Industry editedIndustry) {
        if (industryRepository.getOne(editedIndustry.getId()) != null) {
            return ResponseEntity.ok().body(industryRepository.save(editedIndustry));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
