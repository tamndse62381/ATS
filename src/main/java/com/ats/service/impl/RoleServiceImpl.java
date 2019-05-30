package com.ats.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.entity.Role;
import com.ats.repository.RoleDao;
import com.ats.service.RoleService;
import com.ats.transformer.RoleTransformer;
import com.ats.dto.RoleDTO;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleTransformer roleTransformer;

	private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

	@Override
	public RoleDTO findRoleById(int roleId) {
		LOGGER.info("Begin findRoleById in RoleService with Role id : " + roleId);
		RoleDTO roleDTO = new RoleDTO();
		try {
			Role role = roleDao.findOne(roleId);
			if (role != null) {
				roleDTO = roleTransformer.convertEntityToDTO(role);
			}
		} catch (Exception e) {
			LOGGER.info(e);
		}
		LOGGER.info("End findRoleById in RoleService with Role id : " + roleId);
		return roleDTO;
	}

}
