package com.ats.ws;

import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyWS {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    // Create a new company
    @PostMapping("")
    public ResponseEntity<Company> create(@RequestBody Company newCompany){
        try {
            newCompany.setCreatedDate(Calendar.getInstance().getTime());
            newCompany.setLastModify(Calendar.getInstance().getTime());
            return ResponseEntity.ok().body(companyRepository.save(newCompany));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // edit info's compnay
    @PutMapping("")
    public ResponseEntity<Company> edit(@RequestBody Company editedCompany){
        try {
            editedCompany.setLastModify(Calendar.getInstance().getTime());
            return ResponseEntity.ok().body(companyRepository.save(editedCompany));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Paging
    @RequestMapping("/pagingAll")
    public ResponseEntity<Page<Company>> test(@PageableDefault Pageable pageable){
        return ResponseEntity.ok().body(companyRepository.findAllPaging(pageable));
    }

    // Get one company by Employerid
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Company> getByEmployerId(@Param("id") int id){
        try {

        } catch (RuntimeException e){

        }
        return null;
    }
}
