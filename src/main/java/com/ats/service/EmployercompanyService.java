package com.ats.service;

import com.ats.dto.EmployercompanyDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmployercompanyService {

    boolean createNewEmployerCompany(EmployercompanyDTO dto);

    int findCompanyById(int id);

    int findCompanyByUserId(int userId);
}
