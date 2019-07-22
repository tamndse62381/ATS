package com.ats.service.impl;

import com.ats.entity.Functionpackage;
import com.ats.entity.Servicefunction;
import com.ats.entity.Servicepackage;
import com.ats.repository.FunctionPackageRepository;
import com.ats.service.FunctionPackageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionPackageServiceImpl implements FunctionPackageService {

    @Autowired
    FunctionPackageRepository functionPackageRepository;

    private static final Logger LOGGER = LogManager.getLogger(FunctionPackageServiceImpl.class);

    @Override
    public boolean createNewFunctionPackage(int packageId, List<Integer> functionIdList) {
        LOGGER.info("Begin createNewFunctionPackage in FunctionPackage Service");
        boolean result = false;
        try {


            for (int i = 0; i < functionIdList.size(); i++) {

                Functionpackage functionpackage = new Functionpackage();
                Servicepackage servicepackage = new Servicepackage();
                servicepackage.setId(packageId);
                functionpackage.setServicepackageByServicePackageId(servicepackage);
                Servicefunction servicefunction = new Servicefunction();
                servicefunction.setId(functionIdList.get(i));
                functionpackage.setServicefunctionByServiceFunctionId(servicefunction);

                functionPackageRepository.save(functionpackage);
                result = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End createNewFunctionPackage in FunctionPackage Service");
        return result;
    }
}
