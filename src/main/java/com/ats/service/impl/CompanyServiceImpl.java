package com.ats.service.impl;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.dto.CompanyDTO3;
import com.ats.dto.CompanyindustryDTO;
import com.ats.entity.Company;
import com.ats.entity.Companyindustry;
import com.ats.repository.CityRepository;
import com.ats.repository.CompanyRepository;
import com.ats.repository.CompanyindustryRepository;
import com.ats.repository.IndustryRepository;
import com.ats.service.CompanyService;
import com.ats.util.RestResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CompanyindustryRepository companyindustryRepository;
    @Autowired
    private IndustryRepository industryRepository;

    private static final Logger LOGGER = LogManager.getLogger(CompanyServiceImpl.class);

    //Mapping Object
    ModelMapper modelMapper = new ModelMapper();
    // Conts
    private static String EMAIL = "1101010001001101000000000000";

    @Override
    public Company findComanyByID(int id) {
        return companyRepository.findOne(id);
    }

    @Override
    public List<CompanyDTO2> listAll() {
        ModelMapper mapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<CompanyDTO2>>() {
        }.getType();
        List<CompanyDTO2> listofDTO = null;
        List<Company> listCompany = companyRepository.findAll();
        List<Company> tempCompanyList = new ArrayList<>();
        for (int i = 0; i < listCompany.size(); i++) {
            if (listCompany.get(i).getStatus().equals("approved")) {
                tempCompanyList.add(listCompany.get(i));
            }
        }
        if (tempCompanyList.size() > 0) {
            listofDTO = mapper.map(tempCompanyList, targetListType);
        }

        return listofDTO;
    }

    @Override
    public List<CompanyDTO3> listAllAdmin() {
        ModelMapper mapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<CompanyDTO3>>() {
        }.getType();
        List<CompanyDTO3> listofDTO;
        List<Company> listCompany = companyRepository.findAll();
        listofDTO = mapper.map(listCompany, targetListType);
        return listofDTO;
    }


    @Override
    public RestResponse create(CompanyDTO newCompany) {
        // check name's company
        Company check = companyRepository.findByNameCompany(newCompany.getNameCompany());
        if (check != null)
            return new RestResponse(false, "Tên công ty đã tồn tại!!!", null);
        Company company = new Company();
        company = modelMapper.map(newCompany, Company.class);
        company.setEmail(EMAIL);
        company.setCityByCityId(cityRepository.findOne(newCompany.getCityId()));
        company.setStatus(newCompany.getStatus());
        company.setCreatedDate(new Timestamp(new Date().getTime()));
        companyRepository.save(company);
        Company changeCompany = companyRepository.findByEmail(EMAIL);
        int companyId = changeCompany.getId();
        changeCompany.setEmail(newCompany.getEmail());
        companyRepository.save(changeCompany);

        // Save list
        Company savedCompany = companyRepository.findOne(companyId);
        // mapping Company va industry
        List<CompanyindustryDTO> list = newCompany.getCompanyindustriesById();
        if (list != null) {
            for (CompanyindustryDTO companyindustryDTO : list) {
                Companyindustry com = modelMapper.map(companyindustryDTO, Companyindustry.class);
                com.setCompanyId(companyId);
                com.setIndustryByIndustryId(industryRepository.findOne(com.getIndustryId()));
                com.setCompanyByCompanyId(savedCompany);
                companyindustryRepository.save(com);
            }
        }
        return new RestResponse(true, "Tạo công ty thành công!!!", companyId);
    }

    @Override
    public int changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in Company Service with Job id - newStatus : {}", id + newStatus);
        int success;
        success = companyRepository.changeStatus(id, newStatus);
        LOGGER.info("End changeStatus in Company Service with result: {}", success);
        return success;
    }

    @Override
    public Page<CompanyDTO3> findAllCompanyByStatus(String search, String status, Pageable pageable) {
        LOGGER.info("Begin findAllCompanyByStatus in Company Service with search value - newStatus : {}",
                search + status);
        Page<Company> companyPage = null;
        List<CompanyDTO3> companyDTO3List = null;
        Page<CompanyDTO3> companyDTO3Page = null;
        try {
            companyPage = companyRepository.findAll(pageable, search, status);
            List<String> userOfMainEmployer = new ArrayList<>();

            ModelMapper mapper = new ModelMapper();
            java.lang.reflect.Type targetListType = new TypeToken<List<CompanyDTO3>>() {
            }.getType();
            companyDTO3List = mapper.map(companyPage.getContent(), targetListType);
            for (int i = 0; i < companyPage.getContent().size(); i++) {
                for (int j = 0; j < companyPage.getContent().get(i).getEmployercompaniesById().size(); j++) {
                    if (companyPage.getContent().get(i).getEmployercompaniesById().get(j).getUsersByUserId().getRoleId() == 2) {
                        if(companyDTO3List.get(i).getId() == companyPage.getContent().get(i).getId()){
                            companyDTO3List.get(i).setUsersEmail(companyPage.
                                    getContent().get(i).getEmployercompaniesById().get(j).getUsersByUserId().getEmail());
                        }

                    }
                }
            }
            companyDTO3Page = new PageImpl<>(companyDTO3List, new PageRequest(companyPage.getTotalPages(), companyPage.getSize()), companyDTO3List.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("End findAllCompanyByStatus in Company Service with search value - newStatus : {}",
                search + status);
        return companyDTO3Page;

    }
}
