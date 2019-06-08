package com.ats.ws;

import com.ats.entity.Skilltype;
import com.ats.service.SkilltypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skilltype")
public class SkilltypeWS {
    @Autowired
    private SkilltypeService skilltypeService;

    // CREATE A NEW SKILLTYPE DO MAY BAN
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "")
    public boolean createANewSkilltype(@RequestBody Skilltype newSkilltype) {
        try {
            skilltypeService.createANewSkilltype(newSkilltype);
            return true;
        } catch (RuntimeException e){

        }
        return false;
    }

    // EDIT SKILL TYPE (DOI TEN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public boolean editSkilltype(@RequestBody Skilltype editedSkilltype, @PathVariable int id){
        if (skilltypeService.editASkilltype(editedSkilltype, id))
            return true;
        return false;
    }
}
