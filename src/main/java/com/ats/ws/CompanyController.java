package com.ats.ws;

import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

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
}
