package com.ats.service;

import com.ats.dto.CompanyDTO;
import com.ats.entity.Company;
import com.ats.model.FileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    Company findComanyByEmployerID(@Param("id") int id);

    CompanyDTO create(CompanyDTO newCompany, FileModel file);
}
