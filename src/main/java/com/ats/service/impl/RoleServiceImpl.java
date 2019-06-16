package com.ats.service.impl;

import com.ats.entity.Role;
import com.ats.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ats.service.RoleService;
import com.ats.dto.RoleDTO;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    ModelMapper modelMapper;
    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    @Override
    public RoleDTO findRoleById(int roleId) {
        LOGGER.info("Begin findRoleById in RoleService with Role id : " + roleId);
        RoleDTO roleDTO = new RoleDTO();
        modelMapper = new ModelMapper();
        try {
            Role role = roleRepository.getOne(roleId);
            if (role != null) {
                roleDTO = modelMapper.map(role,RoleDTO.class);
            }
        } catch (Exception e) {
            LOGGER.info(e);
        }
        LOGGER.info("End findRoleById in RoleService with Role id : " + roleId);
        return roleDTO;
    }

}
