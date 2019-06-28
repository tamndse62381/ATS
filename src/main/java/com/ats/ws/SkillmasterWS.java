package com.ats.ws;

import com.ats.dto.SkillMasterDTO;
import com.ats.entity.Skill;
import com.ats.entity.Skillmaster;
import com.ats.service.SkillmasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skillmaster")
public class SkillmasterWS {
    @Autowired
    private SkillmasterService skillmasterService;

    // LIST RA HET BO VO DROP DOWN CHO NGUOI NHAP CHON
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<List<SkillMasterDTO>> getAll(){
        return ResponseEntity.ok().body(skillmasterService.listAll());
    }

    // CREATE A NEW SKILL DO MAY BAN
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public boolean createNewSkillmaster(@RequestBody Skillmaster newSkillmaster){
        if (skillmasterService.createANewSkillMaster(newSkillmaster))
            return true;
        return false;
    }

    // EDIT MOT SKILL IN ONE CV
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public boolean editASkillmaster(@RequestBody Skillmaster editedSkillmaster, @PathVariable int id){
           if (skillmasterService.editASkillmaster(editedSkillmaster, id))
               return true;
           return false;
    }

    // GET All LANGUAGE SKILL
    @RequestMapping(value = "/language/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<List<Skillmaster>> findAllLanguageskill(@PathVariable int id){
        return ResponseEntity.ok().body(skillmasterService.findAllLanguageskill(id));
    }
}
