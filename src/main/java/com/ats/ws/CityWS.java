package com.ats.ws;


import com.ats.entity.City;
import com.ats.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityWS {

    @Autowired
    CityService cityService;

    private static final Logger LOGGER = LogManager.getLogger(CityWS.class);

    @CrossOrigin(origins = "localhost:8090")
    @GetMapping(value = "/getAllCity" ,produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<City>> getAllCity() {
        LOGGER.info("Begin getAllCity in CityWS ");
        List<City> cityList = new ArrayList<>();
        try {
            cityList = cityService.getAllCity();
            LOGGER.info("End get AllCityin in CityWS ");
            if (cityList.size() > 0) {
                return ResponseEntity.ok(cityList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

}
