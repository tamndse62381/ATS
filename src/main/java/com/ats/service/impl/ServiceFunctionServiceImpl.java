package com.ats.service.impl;

import com.ats.entity.Servicefunction;
import com.ats.repository.ServiceFunctionRepository;
import com.ats.service.ServiceFunctionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFunctionServiceImpl implements ServiceFunctionService {

    @Autowired
    ServiceFunctionRepository serviceFunctionRepository;

    private static final Logger LOGGER = LogManager.getLogger(ServiceFunctionServiceImpl.class);

    @Override
    public List<Servicefunction> getAllService() {
        List<Servicefunction> servicefunctions = null;
        LOGGER.info("Begin getAllService in ServiceFunction Service ");
        try {
            servicefunctions = serviceFunctionRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllService in ServiceFunction Service ");
        return servicefunctions;
    }
}
