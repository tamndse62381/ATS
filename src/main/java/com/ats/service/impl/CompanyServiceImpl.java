package com.ats.service.impl;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.dto.CompanyindustryDTO;
import com.ats.entity.Company;
import com.ats.entity.Companyindustry;
import com.ats.repository.CityRepository;
import com.ats.repository.CompanyRepository;
import com.ats.repository.CompanyindustryRepository;
import com.ats.repository.IndustryRepository;
import com.ats.service.CompanyService;
import com.ats.util.RestResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<CompanyDTO2> listofDTO;
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
        company.setStatus("1");
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
        if (list != null){
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
}
