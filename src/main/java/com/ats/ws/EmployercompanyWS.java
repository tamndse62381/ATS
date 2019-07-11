package com.ats.ws;

import com.ats.dto.EmployercompanyDTO;
import com.ats.service.CompanyService;
import com.ats.service.EmployercompanyService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/employercompany")
public class EmployercompanyWS {
    @Autowired
    EmployercompanyService employercompanyService;

    private static final Logger LOGGER = LogManager.getLogger(EmployercompanyWS.class);

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addNewEmployerCompany" )
    public RestResponse addNewEmployerCompany(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
        try {
            dto.setCreatedDate(new Timestamp(new Date().getTime()));
            boolean finish = employercompanyService.createNewEmployerCompany(dto);
            LOGGER.info("End addNewEmployerCompany in EmployercompanyWS with User id : {}" + dto.getUserId());
            if (finish) {
                return new RestResponse(true, "Create New Employer Company Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Employer Company ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/getCompanyId")
    public RestResponse getCompany(@RequestBody EmployercompanyDTO dto) {
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with Job id : {}" + dto.getUserId());
        try {

            int result = employercompanyService.findCompanyByUserId(dto.getUserId());
            if (result > -1) {
                return new RestResponse(true, "Get Company Id Successful", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Employer Company ", null);
    }


}
