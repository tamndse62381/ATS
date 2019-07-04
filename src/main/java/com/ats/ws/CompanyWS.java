package com.ats.ws;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyindustryDTO;
import com.ats.entity.Company;
import com.ats.model.FileModel;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/company")
public class CompanyWS {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    //Create a new company
    @PostMapping("")
    @CrossOrigin("")
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO newCompany
                                             ,@RequestParam("file") MultipartFile file
                                             ,BindingResult result){
        FileModel fileModel = new FileModel();
        fileModel.setFile(file);
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(companyService.create(newCompany, fileModel));
    }

    // edit info's company
    @PutMapping("")
    @CrossOrigin("")
    public ResponseEntity<Company> edit(@RequestBody Company editedCompany){
        try {
            editedCompany.setLastModify(new Timestamp(new Date().getTime()));
            return ResponseEntity.ok().body(companyRepository.save(editedCompany));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Paging
    @RequestMapping("/pagingAll")
    @CrossOrigin("")
    public ResponseEntity<Page<Company>> test(@PageableDefault Pageable pageable){
        return ResponseEntity.ok().body(companyRepository.findAllPaging(pageable));
    }


    // test
    @PostMapping("/testPost")
    public HashMap<String, String> handleUploadFile(@RequestParam("file") MultipartFile file){
        HashMap result = new HashMap<>();
        result.put("status", "success");
        result.put("file", file.getOriginalFilename());
        return result;
    }

    // create temp
    @PostMapping("/create")
    @CrossOrigin("*")
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO newCompany
            ,BindingResult result){
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(companyService.createTemp(newCompany));
    }
}
