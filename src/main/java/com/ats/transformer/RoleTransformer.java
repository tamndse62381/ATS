package com.ats.transformer;

import com.ats.entity.Role;
import org.springframework.stereotype.Service;

import com.ats.dto.RoleDTO;

@Service
public interface RoleTransformer {
	RoleDTO convertEntityToDTO(Role role);

	Role convertDTOToEntity(RoleDTO dto);
}
