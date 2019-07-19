package com.ats.service.impl;

import com.ats.dto.ServicePackageDTO;
import com.ats.repository.ServicepackageRepository;
import com.ats.service.ServicePackageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePackageServiceImpl implements ServicePackageService {

    @Autowired
    ServicepackageRepository servicepackageRepository;

    private static final Logger LOGGER = LogManager.getLogger(ServicePackageServiceImpl.class);

    @Override
    public int createServicePack(ServicePackageDTO servicePackageDTO) {
        return 0;
    }

    @Override
    public int updateServicePack(ServicePackageDTO servicePackageDTO) {
        return 0;
    }

    @Override
    public int changeServicePackStatus(int id, String newStatus) {
        return 0;
    }
}
