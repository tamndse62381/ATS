package com.ats.ws;

import com.ats.dto.CVDTO;
import com.ats.entity.*;
import com.ats.repository.CVRepository;
import com.ats.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVWS {
    @Autowired
    private CVService cvService;
    @Autowired
    private CountcvService countcvService;
    // ModelMapper
    ModelMapper modelMapper = new ModelMapper();
    // Constant
    private static String EMAIL = "1101010001001101000000000000";
    private static final Logger LOGGER = LogManager.getLogger(CVWS.class);

    // Get CV By CVID
    @RequestMapping(value = "/getOne/{CVID}/{EmployerID}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<CVDTO> getCV(@PathVariable int CVID,
                                       @PathVariable int EmployerId,
                                       BindingResult result){
        if (result.hasErrors()){
            LOGGER.info("Error in CVWS- getOne: " + result);
        }
        countcvService.countWhenEmployerGetDetailOfCV(CVID, EmployerId);
        return cvService.getCVByCVID(CVID);
    }

    // Create A New CV
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public boolean createANewCV(@RequestBody CVDTO newCV){
        return cvService.create(newCV);
    }

    // Delete One CV
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public ResponseEntity<CVDTO> deleteACV(@RequestBody CVDTO deletedCVDTO){
        return null;
    }


    // Edit information's cv, without list belong to this cv
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public boolean  editCv(@RequestBody CVDTO editedCv){
        return false;
    }
}
