package com.ats.service.impl;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findComanyByEmployerID(int id) {
        return companyRepository.findComanyByEmployerID(id);
    }

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
}
