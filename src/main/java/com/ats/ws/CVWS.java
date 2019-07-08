package com.ats.ws;

import com.ats.dto.CVDTO;
import com.ats.entity.*;
import com.ats.form.CreateCVForm;
import com.ats.repository.CVRepository;
import com.ats.service.*;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVWS {
    @Autowired
    private CVService cvService;
    @Autowired
    private CountcvService countcvService;

    private static final Logger LOGGER = LogManager.getLogger(CVWS.class);

    // Get CV By CVID -- tested
    @RequestMapping(value = "/getOne/{CVID}/{EmployerID}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public RestResponse getCV(@PathVariable(name = "CVID") Integer CVID,
                                       @PathVariable(name = "EmployerID") Integer EmployerId){
        if (EmployerId == 0 ){
            return cvService.getCVByCVID(CVID);
        } else {
            countcvService.countWhenEmployerGetDetailOfCV(CVID, EmployerId);
            return cvService.getCVByCVID(CVID);
        }
    }

    // GET List CV cho User xem bang UserID - tested - co status = 1 (valid)
    @GetMapping("/get-list/{id}")
    @CrossOrigin
    public RestResponse getListCvByUserId(@PathVariable(name = "id") int UserId){
        return cvService.getlistCvByUserId(UserId);
    }

    // Delete One CV
    @RequestMapping(value = "/deleteCV/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public RestResponse deleteACV(@PathVariable int id){
        return cvService.delete(id);
    }


    // Edit information's cv, without list belong to this cv
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public boolean  editCv(@RequestBody CVDTO editedCv){
        return false;
    }

    // Test
    @Autowired
    private CVRepository cvRepository;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<Cv> test(){
        return cvRepository.findAll();
    }

    // Create New Cv
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @CrossOrigin("*")
    @ResponseBody
    public RestResponse create(@Valid @RequestBody  CVDTO newCv,
                                    BindingResult result){
        if (result.hasErrors())
            return new RestResponse(false ,"ERROR: " + result.toString(), null);
        return cvService.create(newCv);
    }
}
