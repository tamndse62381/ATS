package com.ats.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ats.dto.CompanyDTO;
import com.ats.dto.CompanyDTO2;
import com.ats.dto.UsersDTO2;
import com.ats.entity.City;
import com.ats.entity.Company;
import com.ats.entity.Joblevel;
import com.ats.service.CityService;
import com.ats.service.CompanyService;
import com.ats.service.JoblevelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ats.dto.UsersDTO;
import com.ats.service.UsersService;
import com.ats.token.TokenAuthenticationService;
import com.ats.util.RestResponse;

@RestController
@RequestMapping("/user")
public class UserWS {

    @Autowired
    UsersService usersService;
    @Autowired
    TokenAuthenticationService tokenService;
    @Autowired
    JoblevelService joblevelService;
    @Autowired
    CityService cityService;
    @Autowired
    CompanyService companyService;

    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/login")
    @ResponseBody
    public RestResponse login(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin login inUserWS with username - password: {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());

        try {
            if(!usersDTO.getPassword().isEmpty()){
                usersDTO = usersService.login(usersDTO.getEmail(), usersDTO.getPassword());
                LOGGER.info("End login in UserWS with username ", usersDTO.getEmail() );
                if (usersDTO != null && usersDTO.getId() != 0) {
                    return new RestResponse(true, "Login Successful", usersDTO);
                }
            } else {
                return new RestResponse(false, "Null Password", usersDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Login Fail Because " + usersDTO.getEmail(), null);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/registration")
    public RestResponse registration(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin Registration in UserWS with email - password - fullname: {}",
                usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());
        int result;
        try {
            java.util.Date date = new java.util.Date();
            String tokenString = tokenService.addAuthentication(usersDTO.getEmail());
            usersDTO.setAccessToken(tokenString);
            usersDTO.setLastLogin(date);
            usersDTO.setCreatedDate(date);
            usersDTO.setStatus("new");
            result = usersService.registration(usersDTO);
            LOGGER.info("End Registration in UserWS with email - password - fullname: {}",
                    usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());

            if (result > -1) {
                return new RestResponse(true, "Create To Successful", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To User", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/checkLogin", produces = "application/json;charset=UTF-8")
    public RestResponse checkLogin(@RequestHeader(value = "accessToken") String accessToken) {
        LOGGER.info("Begin login in UserWS with Token : {}", accessToken);
        UsersDTO2 usersDTO;
        try {
            usersDTO = usersService. findUserByToken(accessToken);
            LOGGER.info("End login in UserWS with Token : {}", accessToken);
            if (usersDTO != null) {
                return new RestResponse(true, "CheckLogin To Successful", usersDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "CheckLogin To Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "localhost:8090")
    @PostMapping(value = "/changePassword", produces = "application/json;charset=UTF-8")
    public RestResponse changePassword(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin changePassword in UserWS with Account id {}" + usersDTO.getId());
        int success;
        try {
            success = usersService.changePassword(usersDTO.getId(), usersDTO.getNewPassword(), usersDTO.getPassword());
            if (success > 0) {
                return new RestResponse(true, "changePassword Successful with new password is " + usersDTO.getNewPassword(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changePassword in UserWS with Account id {}" + usersDTO.getId());
        return new RestResponse(false, "changePassword Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "localhost:8090")
    @PostMapping(value = "/changeUserStatus", produces = "application/json;charset=UTF-8")
    public RestResponse changeStatus(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin changeStatus in UserWS with Account id : {}" + usersDTO.getId());
        int success;
        try {
            success = usersService.changeStatus(usersDTO.getId(), usersDTO.getStatus());
            if (success > 0) {
                return new RestResponse(true, "changeStatus Successful with status " + usersDTO.getStatus(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin changeStatus in UserWS with Account id : {}" + usersDTO.getId());
        return new RestResponse(false, "changeStatus Fail", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/registrationGoogle")
    public RestResponse registrationGoogle(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin Registration in UserWS with email - password - fullname: {}",
                usersDTO.getEmail()  + " - " + usersDTO.getFullname());
        int result;
        try {
            java.util.Date date = new java.util.Date();
            if(usersDTO.getPassword().isEmpty()){
                String tokenString = tokenService.addAuthentication(usersDTO.getEmail());
                usersDTO.setAccessToken(tokenString);
                usersDTO.setLastLogin(date);
                usersDTO.setCreatedDate(date);
                usersDTO.setStatus("new");
                result = usersService.registration(usersDTO);
                LOGGER.info("End Registration in UserWS with email - password - fullname: {}",
                        usersDTO.getEmail() + " - " + usersDTO.getFullname());

                if (result > -1) {
                    return new RestResponse(true, "Create To Successful", null);
                }
            }else {
                    return new RestResponse(false, "Create Fail", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To Account", null);
    }

    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/loginGoogle")
    @ResponseBody
    public RestResponse loginGoogle(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin loginGoogle inUserWS with username - password: {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());
        try {
                usersDTO = usersService.login(usersDTO.getEmail(), usersDTO.getPassword());
                LOGGER.info("End loginGoogle in UserWS with username - password : {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());
                if (usersDTO != null && usersDTO.getId() != 0) {
                    return new RestResponse(true, "Login Successful", usersDTO);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Login Fail Because " + usersDTO.getEmail(), null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getRegisterEmployerComponent")
    public RestResponse getRegisterEmployerComponent(){
        LOGGER.info("Begin getRegisterEmployerComponent in UserWS");
        List<City> listCity;
        List<Joblevel> listJobLevel;
        List<CompanyDTO2> listCompany;
        try {
            listCity = cityService.getAllCity();
            listCompany = companyService.listAll();
            listJobLevel = joblevelService.getAllJobLevel();
            HashMap<String,List> map = new HashMap<>();
            map.put("city", listCity);
            map.put("level" , listJobLevel);
            map.put("company" , listCompany);
            return new RestResponse(true, "Get Register Employer Component Successful", map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End getRegisterEmployerComponent in UserWS");

        return new RestResponse(false, "Job is Not Available : ", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/updateUser")
    public RestResponse updateUser(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin updateUser in UserWS with email - password - fullname: ", usersDTO.getFullname());
        int result;
        try {
            result = usersService.updateUser(usersDTO);
            LOGGER.info("End updateUser in UserWS with email - password - fullname: {}",
                    usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());

            if (result > -1) {
                return new RestResponse(true, "Update Successful", usersDTO.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Update To User", null);
    }
}
