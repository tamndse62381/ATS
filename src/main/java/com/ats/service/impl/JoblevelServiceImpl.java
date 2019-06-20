package com.ats.service.impl;


import com.ats.dto.RoleDTO;
import com.ats.entity.Joblevel;
import com.ats.entity.Role;
import com.ats.repository.JoblevelRepository;
import com.ats.service.JoblevelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JoblevelServiceImpl implements JoblevelService {

    @Autowired
    JoblevelRepository joblevelRepository;

    private static final Logger LOGGER = LogManager.getLogger(JoblevelServiceImpl.class);

    @Override
    public List<Joblevel> getAllJobLevel() {
        LOGGER.info("Begin getJobLevelById in JoblevelService");
        List joblevellist = new ArrayList<Joblevel>();
        try {
            joblevellist = joblevelRepository.findAll();
        } catch (Exception e) {
            LOGGER.info(e);
        }
        LOGGER.info("End getAllJobLevel in JoblevelService");
        return joblevellist;

    }
}