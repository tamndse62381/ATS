package com.ats.transformer;

import org.springframework.stereotype.Service;

import com.ats.dto.RoleDTO;
import com.ats.entity.Role;

@Service
public interface RoleTransformer {
	RoleDTO convertEntityToDTO(Role role);

	Role convertDTOToEntity(RoleDTO dto);
}
