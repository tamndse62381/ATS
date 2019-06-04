package com.ats.transformer;

import com.ats.dto.CompanyDTO;
import com.ats.dto.RoleDTO;
import com.ats.entity.Company;
import com.ats.entity.Role;

public interface CompanyTransformer {
	CompanyDTO convertEntityToDTO(Company company);

	Company convertDTOToEntity(CompanyDTO dto);
}
