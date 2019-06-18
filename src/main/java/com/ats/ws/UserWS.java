package com.ats.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ats.entity.Users;
//import com.ats.specification.UserSpecificationsBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/login")
    @ResponseBody
    public RestResponse login(@RequestBody UsersDTO usersDTO) {
        LOGGER.info("Begin login inUserWS with username - password: {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());

        try {
            usersDTO = usersService.login(usersDTO.getEmail(), usersDTO.getPassword());
            LOGGER.info("End login in UserWS with username - password : {}", usersDTO.getEmail() + " - " + usersDTO.getPassword());
            if (usersDTO != null && usersDTO.getId() != 0) {
                return new RestResponse(true, "Login Successful", usersDTO);
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
            String tokenString = tokenService.addAuthentication(usersDTO.getEmail());
            usersDTO.setAccessToken(tokenString);
            result = usersService.registration(usersDTO);
            LOGGER.info("End Registration in UserWS with email - password - fullname: {}",
                    usersDTO.getEmail() + " - " + usersDTO.getPassword() + " - " + usersDTO.getFullname());

            if (result > -1) {
                return new RestResponse(true, "Create To Successful", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To Account", null);
    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping(value = "/checkLogin", produces = "application/json;charset=UTF-8")
    public RestResponse checkLogin(@RequestHeader(value = "accessToken") String accessToken) {
        LOGGER.info("Begin login in UserWS with Token : {}", accessToken);
        UsersDTO usersDTO;
        try {
            usersDTO = usersService.findAccountByToken(accessToken);
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
    @PostMapping(value = "/changeStatus", produces = "application/json;charset=UTF-8")
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

//    @CrossOrigin(origins = "localhost:8090")
//    @GetMapping(value = "/search")
//    @ResponseBody
//    public List<UsersDTO> searchUser(@RequestParam(value = "search") String search) {
//        LOGGER.info("Begin searchUser in AccountWS with Search value : {}" + search);
//        List<UsersDTO> listUser = new ArrayList<>();
//        try {
//            listUser = usersService.searchUser(search);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        LOGGER.info("End searchUser in AccountWS with Search value : {}" + search);
//        return listUser;
//    }

}
