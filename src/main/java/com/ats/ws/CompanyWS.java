package com.ats.ws;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.dto.CompanyindustryDTO;
import com.ats.entity.Company;
import com.ats.model.FileModel;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import com.ats.util.RestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyWS {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    //Create a new company
    @PostMapping("")
    @CrossOrigin("*")
    public RestResponse create(@Valid @RequestBody CompanyDTO newCompany, BindingResult result) {
        if (result.hasErrors())
            return null;
        return companyService.create(newCompany);
    }

    // edit info's company
    @PutMapping("")
    @CrossOrigin("")
    public ResponseEntity<Company> edit(@RequestBody Company editedCompany) {
        try {

            editedCompany.setLastModify(new Timestamp(new Date().getTime()));
            return ResponseEntity.ok().body(companyRepository.save(editedCompany));
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Paging
    @RequestMapping("/pagingAll")
    @CrossOrigin("")
    public ResponseEntity<Page<Company>> test(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(companyRepository.findAllPaging(pageable));
    }

    @GetMapping("/listCompany")
    @CrossOrigin("")
    public ResponseEntity<List<CompanyDTO2>> getCompanyById() {
        List<CompanyDTO2> company = companyService.listAll();
        return new ResponseEntity< List<CompanyDTO2>>(company, HttpStatus.OK);
    }

}
