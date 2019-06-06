package com.ats.transformer.impl;

import com.ats.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ats.dto.RoleDTO;
import com.ats.transformer.RoleTransformer;

@Service
public class RoleTransformerImpl implements RoleTransformer {

	private static final Logger LOGGER = LogManager.getLogger(RoleTransformerImpl.class);

	@Override
	public RoleDTO convertEntityToDTO(Role role) {
		LOGGER.info("Begin convertToDTO in Role Transformer with role Id : " + role.getId());
		RoleDTO roleDTO = null;
		ModelMapper modelMapper = new ModelMapper();
		if (role != null) {
			roleDTO = modelMapper.map(role, RoleDTO.class);
			// roleDTO = new RoleDTO();
			// roleDTO.setId(role.getId());
			// roleDTO.setName(role.getRoleName());

		}
		LOGGER.info("End convertToDTO in Role Transformer with role Id : " + role.getId());
		return roleDTO;
	}

	@Override
	public Role convertDTOToEntity(RoleDTO dto) {
		LOGGER.info("Begin convertToEntity in Role Transformer with roleDTO Id : " + dto.getId());
		Role role = null;
		ModelMapper modelMapper = new ModelMapper();
		if (dto != null) {
			role = modelMapper.map(dto, Role.class);
//			role = new Role();
//			role.setId(dto.getId());
//			role.setRoleName(dto.getName());

		}
		LOGGER.info("End convertToEntity in Role Transformer with roleDTO Id : " + dto.getId());
		return role;
	}

}
