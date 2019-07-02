package com.ats.ws;

import com.ats.dto.EmployercompanyDTO;
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
        LOGGER.info("Begin addNewEmployerCompany in EmployercompanyWS with Job id : {}" + dto.getUserId());
        try {
            dto.setCreatedDate(new Timestamp(new Date().getTime()));
            boolean finish = employercompanyService.createNewEmployerCompany(dto);
            if (finish) {
                return new RestResponse(true, "Create New Employer Company Successfull", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail To Create New Employer Company ", null);
    }
}
