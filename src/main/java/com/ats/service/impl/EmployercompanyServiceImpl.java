package com.ats.service.impl;

import com.ats.dto.EmployercompanyDTO;
import com.ats.dto.EmployercompanyDTO2;
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
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                usersService.changeRole(employercompany.getUserId(), 3);
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
        Employercompany employercompany;
        EmployercompanyDTO employercompanyDTO = null;
        ModelMapper modelMapper = new ModelMapper();
        try {
            employercompany = employercompanyRepository.findCompanyByUserId(userId);
            employercompanyDTO = modelMapper.map(employercompany, EmployercompanyDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End findCompanyByUserId in Employercompany Service with User id : " + userId);
        return employercompanyDTO;
    }

    @Override
    public int changeStatus(EmployercompanyDTO employercompanyDTO) {
        LOGGER.info("Begin findCompanyByUserId in Employercompany Service with User id : " + employercompanyDTO.getUserId());
        int result = -1;
        try {
            if (employercompanyDTO.getStatus().equals("approved")) {
                result = employercompanyRepository.changeStatus(employercompanyDTO.getUserId(), employercompanyDTO.getStatus());
                if (result > -1) {
                    usersService.changeRole(employercompanyDTO.getUserId(), 3);
                }
            } else if (employercompanyDTO.getStatus().equals("denied")) {
                result = employercompanyRepository.changeStatus(employercompanyDTO.getUserId(), employercompanyDTO.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin findCompanyByUserId in Employercompany Service with User id : " + employercompanyDTO.getUserId());
        return result;
    }

    @Override
    public Page<EmployercompanyDTO2> getAllEmployerCompanyByUserId(String search,
                                                                   String status,
                                                                   int userId,
                                                                   Pageable pageable) {
        LOGGER.info("Begin getAllEmployerCompanyByUserId in Employercompany Service with User id : " + userId);
        Page<Employercompany> employercompanyPage;
        Page<EmployercompanyDTO2> employercompanyDTO2Page = null;
        List<EmployercompanyDTO2> listofDTO;
        ModelMapper mapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<EmployercompanyDTO2>>() {
        }.getType();

        try {
            employercompanyPage = employercompanyRepository.getAll(pageable, search, status, userId);

            listofDTO = mapper.map(employercompanyPage.getContent(), targetListType);
            employercompanyDTO2Page = new PageImpl<>(
                    listofDTO,
                    new PageRequest(employercompanyPage.getTotalPages(), employercompanyPage.getSize()), listofDTO.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllEmployerCompanyByUserId in Employercompany Service with User id : " + userId);
        return employercompanyDTO2Page;
    }
}
