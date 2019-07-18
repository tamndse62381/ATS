package com.ats.service;

import com.ats.dto.EmployercompanyDTO;
import com.ats.entity.Employercompany;
import org.springframework.stereotype.Service;

@Service
public interface EmployercompanyService {

    boolean createNewEmployerCompany(EmployercompanyDTO dto);

    boolean createNewEmployerExistedCompany(EmployercompanyDTO dto);

    int findCompanyById(int id);

    EmployercompanyDTO findCompanyByUserId(int userId);
}
