package com.ats.ws;

import com.ats.dto.CVDTO;
import com.ats.entity.*;
import com.ats.model.FileModel;
import com.ats.repository.CVRepository;
import com.ats.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CVWS {
    @Autowired
    private CVService cvService;
    @Autowired
    private CountcvService countcvService;

    private static final Logger LOGGER = LogManager.getLogger(CVWS.class);

    // Get CV By CVID
    @RequestMapping(value = "/getOne/{CVID}/{EmployerID}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<Cv> getCV(@PathVariable(name = "CVID") int CVID,
                                       @PathVariable(name = "EmployerID") int EmployerId,
                                       BindingResult result){
        if (result.hasErrors()) {
            LOGGER.info("Error in CVWS- getOne: " + result);
        }
        if (EmployerId == 0 ){
            return cvService.getCVByCVID(CVID);
        } else {
            countcvService.countWhenEmployerGetDetailOfCV(CVID, EmployerId);
            return cvService.getCVByCVID(CVID);
        }
    }

    // Create A New CV
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "")
    public boolean createANewCV(@RequestBody CVDTO newCV,
                                BindingResult result,
                                FileModel file){
        if (result.hasErrors()){
            return false;
        }
        return cvService.create(newCV, file);
    }

    // Delete One CV
    @RequestMapping(value = "/deleteCV/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public boolean deleteACV(@PathVariable int id, BindingResult result){
        if (result.hasErrors()){
            return false;
        }
        cvService.delete(id);
        return true;
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


    // Post Temp
    @RequestMapping(value = "/testCreate", method = RequestMethod.POST)
    @CrossOrigin("*")
    public boolean testCreate(@RequestBody CVDTO newCV,
                              BindingResult result){
        if (result.hasErrors()){
            return false;
        }
        return cvService.createTemp(newCV);
    }
}
