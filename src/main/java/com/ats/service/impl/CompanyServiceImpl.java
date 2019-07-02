package com.ats.service.impl;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.entity.Company;
import com.ats.model.FileModel;
import com.ats.repository.CityRepository;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.ats.util.FileUltis;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    HttpServletRequest httpServletRequest;
    //Mapping Object
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public Company findComanyByID(int id) {
        return companyRepository.findOne(id);
    }

    @Override
    public List<CompanyDTO2> listAll() {
        ModelMapper mapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<CompanyDTO2>>() {
        }.getType();
        List<CompanyDTO2> listofDTO;
        List<Company> listCompany = companyRepository.findAll();
        listofDTO = mapper.map(listCompany, targetListType);
        return listofDTO;
    }
    
    public CompanyDTO create(CompanyDTO newCompany, FileModel file) {
        Company company = companyRepository.findCompanyByNameCompany(newCompany.getNameCompany());
        if (company != null){
            return null;
        }
        //updaload file
        String uploadPath = httpServletRequest.getRealPath("") + File.separator + "hinhanh" + File.separator;
        //set imgLogo
        String fileName = FileUltis.saveFile(file, uploadPath);
        company.setLogoImg(fileName);
        company = modelMapper.map(newCompany, Company.class);
        company.setCreatedDate(new Timestamp(new Date().getTime()));
        company.setCityByCityId(cityRepository.findOne(newCompany.getCityId()));
        company.setLastModify(new Timestamp(new Date().getTime()));
        companyRepository.save(company);
        return newCompany;
    }
}
