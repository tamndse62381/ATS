package com.ats.service;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.entity.Company;
import com.ats.model.FileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company findComanyByID(int id);

    List<CompanyDTO2> listAll();

    CompanyDTO create(CompanyDTO newCompany, FileModel file);

    CompanyDTO createTemp(CompanyDTO newCompany);
}
