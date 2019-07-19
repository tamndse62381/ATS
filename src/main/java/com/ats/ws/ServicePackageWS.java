package com.ats.ws;

import com.ats.dto.ServicePackageDTO;
import com.ats.service.ServicePackageService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicePack")
public class ServicePackageWS {
    @Autowired
    ServicePackageService servicePackageService;

    private static final Logger LOGGER = LogManager.getLogger(ServicePackageWS.class);

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse createServicePack(@RequestBody ServicePackageDTO servicePackageDTO) {
        LOGGER.info("");

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "createServicePack Fail" , null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/update")
    @ResponseBody
    public RestResponse updateServicePack(@RequestBody ServicePackageDTO servicePackageDTO) {
        LOGGER.info("");

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "updateServicePack Fail " , null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/changeStatus")
    @ResponseBody
    public RestResponse changeStatusServicePack(@RequestBody ServicePackageDTO servicePackageDTO) {
        LOGGER.info("");

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "changeStatusServicePack Fail ", null);
    }

}
