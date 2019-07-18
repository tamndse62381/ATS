package com.ats.service.impl;

import com.ats.dto.EmployercompanyDTO;
import com.ats.entity.Company;
import com.ats.entity.Employercompany;
import com.ats.entity.Users;
import com.ats.repository.EmployercompanyRepository;
import com.ats.service.CompanyService;
import com.ats.service.EmployercompanyService;
import com.ats.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployercompanyServiceImpl implements EmployercompanyService {

    @Autowired
    EmployercompanyRepository employercompanyRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    UsersService usersService;


    private static final Logger LOGGER = LogManager.getLogger(EmployercompanyServiceImpl.class);


    @Override
    public boolean createNewEmployerCompany(EmployercompanyDTO dto) {
        LOGGER.info("Begin createNewEmployerCompany in Employercompany Service with User id : {}" + dto.getUserId());
        Employercompany employercompany;
        boolean result = false;
        try {
            int newid = findCompanyById(dto.getCompanyId());
            if (newid > -1) {
                ModelMapper mapper = new ModelMapper();
                employercompany = mapper.map(dto, Employercompany.class);
                Users users = new Users();
                users.setId(employercompany.getUserId());

                Company company = new Company();
                company.setId(employercompany.getCompanyId());

                employercompany.setCompanyByCompanyId(company);
                employercompany.setUsersByUserId(users);

                employercompany = employercompanyRepository.save(employercompany);
                if (employercompany != null) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End createNewEmployerCompany in Employercompany Service with User id : {}" + dto.getUserId());
        return result;
    }

    @Override
    public boolean createNewEmployerExistedCompany(EmployercompanyDTO dto) {
        LOGGER.info("Begin createNewEmployerCompany in Employercompany Service with User id : {}" + dto.getUserId());
        Employercompany employercompany;
        boolean result = false;
        try {
            int newid = findCompanyById(dto.getCompanyId());
            if (newid > -1) {
                ModelMapper mapper = new ModelMapper();
                employercompany = mapper.map(dto, Employercompany.class);
                Users users = new Users();
                users.setId(employercompany.getUserId());

                Company company = new Company();
                company.setId(employercompany.getCompanyId());

                employercompany.setCompanyByCompanyId(company);
                employercompany.setUsersByUserId(users);

                employercompany = employercompanyRepository.save(employercompany);
                usersService.changeRole(employercompany.getUserId(),3 );
                if (employercompany != null) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End createNewEmployerCompany in Employercompany Service with User id : {}" + dto.getUserId());
        return result;
    }

    @Override
    public int findCompanyById(int id) {
        LOGGER.info("Begin findCompanyById in Employercompany Service with Company id : " + id);
        int result = -1;
        try {
            Company company = companyService.findComanyByID(id);
            if (company != null) {
                result = company.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End findCompanyById in Employercompany Service with Company id : " + id);
        return result;
    }

    @Override
    public EmployercompanyDTO findCompanyByUserId(int userId) {
        LOGGER.info("Begin findCompanyByUserId in Employercompany Service with User id : " + userId);
        Employercompany employercompany ;
        EmployercompanyDTO employercompanyDTO = null;
        ModelMapper modelMapper = new ModelMapper();
        try {
            employercompany  = employercompanyRepository.findCompanyByUserId(userId);
            employercompanyDTO = modelMapper.map(employercompany,EmployercompanyDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End findCompanyByUserId in Employercompany Service with User id : " + userId);
        return employercompanyDTO;
    }
}
