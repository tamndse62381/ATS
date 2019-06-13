package com.ats.ws;


import com.ats.entity.City;
import com.ats.service.CityService;
import com.ats.util.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(value = "/getAllCity")
    @ResponseBody
    public RestResponse getAllCity() {
        LOGGER.info("Begin getAllCity in CityWS ");
        List<City> cityList = new ArrayList<>();
        try {
            cityList = cityService.getAllCity();
            LOGGER.info("End get AllCityin in CityWS ");
            if (cityList.size() > 0) {
                return new RestResponse(true, "Get Successful", cityList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Get Fail ", null);
    }

}
