package com.ats.service;

import org.springframework.stereotype.Service;

import com.ats.dto.RoleDTO;

@Service
public interface RoleService{
	RoleDTO findRoleById(int roleId);
}
