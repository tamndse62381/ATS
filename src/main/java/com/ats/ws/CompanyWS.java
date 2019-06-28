package com.ats.ws;

import com.ats.dto.CompanyDTO;
import com.ats.entity.City;
import com.ats.entity.Company;
import com.ats.entity.Users;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/company")
public class CompanyWS {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    // Create a new company
    @PostMapping("")
    public ResponseEntity<Company> create(@RequestBody CompanyDTO companyDTO){
        try {
            companyDTO.setCreatedDate(new Timestamp(new Date().getTime()));
            companyDTO.setLastModify(new Timestamp(new Date().getTime()));
            ModelMapper mapper = new ModelMapper();
            Company newCompany = mapper.map(companyDTO,Company.class);
            Users users = new Users();
            users.setId(newCompany.getUserId());
            System.out.println("user : " + newCompany.getUserId());

            City city = new City();
            city.setId(newCompany.getCityId());
            System.out.println("city : " + newCompany.getCityId());

            newCompany.setCityByCityId(city);
            newCompany.setUsersByUserId(users);

            return ResponseEntity.ok().body(companyRepository.save(newCompany));
        } catch (RuntimeException e){
            System.out.println(e);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // edit info's company
    @PutMapping("")
    public ResponseEntity<Company> edit(@RequestBody Company editedCompany){
        try {
//            Date date = new Date();
//            Timestamp ts=new Timestamp(date.getTime());
//            System.out.println(ts);
            editedCompany.setLastModify(new Timestamp(new Date().getTime()));
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
    ResponseEntity<Company> getByEmployerId(@PathVariable("id") int id){
        return null;
    }
}
