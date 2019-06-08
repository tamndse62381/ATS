package com.ats.service;

import com.ats.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    public Company findComanyByEmployerID(@Param("id") int id);
}
