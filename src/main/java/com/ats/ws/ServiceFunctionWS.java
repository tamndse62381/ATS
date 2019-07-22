package com.ats.ws;

import com.ats.entity.Servicefunction;
import com.ats.service.ServiceFunctionService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceFunctionWS {
    @Autowired
    ServiceFunctionService serviceFunctionService;

    private static final Logger LOGGER = LogManager.getLogger(ServiceFunctionWS.class);

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getAll")
    public RestResponse getAllFunction() {
        LOGGER.info("Begin getAllFunction in ServiceFunctionWS");
        List<Servicefunction> servicefunctions = null;
        try {
            servicefunctions = serviceFunctionService.getAllService();
            return new RestResponse(true, "Get getAllFunction Successful", servicefunctions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllFunction in ServiceFunctionWS");
        return new RestResponse(false, "No Function is Available : ", null);
    }
}
