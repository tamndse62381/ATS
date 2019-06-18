package com.ats.service.impl;

import com.ats.entity.Company;
import com.ats.repository.CompanyRepository;
import com.ats.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findComanyByEmployerID(int id) {
        return companyRepository.findComanyByEmployerID(id);
    }
}
