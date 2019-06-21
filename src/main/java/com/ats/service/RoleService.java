package com.ats.service;

import com.ats.entity.Role;
import org.springframework.stereotype.Service;

import com.ats.dto.RoleDTO;

@Service
public interface RoleService{
	Role findRoleById(int roleId);
}
