package com.ats.transformer.impl;

import org.springframework.stereotype.Service;

import com.ats.dto.CompanyDTO;
import com.ats.entity.Company;
import com.ats.service.impl.CompanyServiceImpl;
import com.ats.transformer.CompanyTransformer;

@Service
public class CompanyTransformerImpl implements CompanyTransformer{

	@Override
	public CompanyDTO convertEntityToDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (company != null) {
			companyDTO.setId(company.getId());
			companyDTO.setNameCompany(company.getNameCompany());
			companyDTO.setLogoImg(company.getLogoImg());
		} 
		return companyDTO;
	}

	@Override
	public Company convertDTOToEntity(CompanyDTO dto) {
		return null;
	}

}
