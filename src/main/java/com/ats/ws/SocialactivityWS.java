package com.ats.ws;

import com.ats.entity.Socialactivity;
import com.ats.service.SocialactivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/")
public class SocialactivityWS {
    @Autowired
    private SocialactivityService socialactivityService;

    // find list socialactivity of one CV
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity<List<Socialactivity>> findByCVID (@PathVariable int id){
        return  ResponseEntity.ok().body(socialactivityService.findAllSocialactivityByCVID(id));
    }

    // edit one social activity
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @CrossOrigin(origins = "")
    public boolean editASocialactivity(@RequestBody Socialactivity editedSocialactivity, @PathVariable int id){
        if (socialactivityService.editASocialactivity(editedSocialactivity, id))
            return true;
        return false;
    }

    // Create a new SocialActivity
    @RequestMapping(value = "", method = RequestMethod.POST)
    @CrossOrigin (origins = "")
    public ResponseEntity<Socialactivity> creaete(@RequestBody Socialactivity newSocialactivity){
        if (socialactivityService.createANewSocialactivity(newSocialactivity) != null)
            return ResponseEntity.ok().body(newSocialactivity);
        return ResponseEntity.badRequest().body(null);
    }


    // find one socialactivity by ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "")
    public ResponseEntity findOneByID(@PathVariable int id){
        Socialactivity soc = socialactivityService.findOneByID(id);
        if(soc != null)
            return ResponseEntity.ok().body(soc);
        return ResponseEntity.badRequest().body(null);
    }

    // Delete one socialactivity by ID


}
