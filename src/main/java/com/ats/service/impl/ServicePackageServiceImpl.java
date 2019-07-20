package com.ats.service.impl;

import com.ats.dto.ServicePackageDTO;
import com.ats.entity.Servicepackage;
import com.ats.repository.ServicepackageRepository;
import com.ats.service.ServicePackageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicePackageServiceImpl implements ServicePackageService {

    @Autowired
    ServicepackageRepository servicepackageRepository;

    private static final Logger LOGGER = LogManager.getLogger(ServicePackageServiceImpl.class);

    @Override
    public int createServicePack(ServicePackageDTO servicePackageDTO) {
        LOGGER.info("Begin Create Service Pack in ServicePackage Service ");
        int result = -1;
        ModelMapper mapper = new ModelMapper();
        Servicepackage servicepackage;
        try {
            servicepackage = mapper.map(servicePackageDTO, Servicepackage.class);
            if (servicepackage != null) {
                servicepackage = servicepackageRepository.save(servicepackage);
                result = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End Create Service Pack in ServicePackage Service ");
        return result;
    }

    @Override
    public int updateServicePack(ServicePackageDTO servicePackageDTO) {
        LOGGER.info("Begin Update Service Pack in ServicePackage Service ");
        int result = -1;
        Servicepackage servicepackage;
        try {
            servicepackage = servicepackageRepository.findOne(servicePackageDTO.getId());
            servicepackage.setDescription(servicePackageDTO.getDescription());
            servicepackage.setDuration(servicePackageDTO.getDuration());
            servicepackage.setName(servicePackageDTO.getName());
            servicepackage.setPrice(servicePackageDTO.getPrice());
            if (servicepackage != null) {
                servicepackage = servicepackageRepository.save(servicepackage);
                result = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End Update Service Pack in ServicePackage Service ");
        return result;
    }

    @Override
    public int changeServicePackStatus(int id, String newStatus) {
        LOGGER.info("Begin Change Status Service Pack in ServicePackage Service ");
        int result = -1;
        try {
            result = servicepackageRepository.changeStatus(id, newStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End Change Status Service Pack in ServicePackage Service ");
        return result;
    }

    @Override
    public Page<Servicepackage> getAllServicePack(Pageable pageable) {
        LOGGER.info("Begin Get All Service Pack in ServicePackage Service ");
        Page<Servicepackage> servicepackages = null;
        try {
            servicepackages = servicepackageRepository.findAll(pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End Get All Service Pack in ServicePackage Service ");
        return servicepackages;
    }

    @Override
    public Servicepackage getServicePackDetail(int id) {
        LOGGER.info("Begin Get All Service Pack in ServicePackage Service ");
        Servicepackage servicepackages = null;
        try {
            servicepackages = servicepackageRepository.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End Get All Service Pack in ServicePackage Service ");
        return servicepackages;
    }
}
