package com.ats.ws;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ats.dto.UsersDTO;

import com.ats.service.UserService;
import com.ats.token.TokenAuthenticationService;
import com.ats.util.RestResponse;

@RestController
@RequestMapping("/user")
public class UserWS {

    @Autowired
    UserService userService;
    @Autowired
    TokenAuthenticationService tokenService;
    private static final Logger LOGGER = LogManager.getLogger(UserWS.class);

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(value = "/login")
    @ResponseBody
    public RestResponse login(@RequestParam("email") String email, @RequestParam("password") String password) {
        LOGGER.info("Begin login in Account WS with username - password: {}", email + " - " + password);
        UsersDTO usersDTO = new UsersDTO();
        try {
            usersDTO = userService.login(email, password);
            LOGGER.info("End login in Account WS with username - password : {}", email + " - " + password);
            if (usersDTO != null && usersDTO.getId() != 0) {
                return new RestResponse(true, "Login Successful", usersDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Login Fail Because " + usersDTO.getEmail(), null);
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(value = "/registration", produces = "application/json;charset=UTF-8")
    public RestResponse registration(@RequestParam("email") String email, @RequestParam("password") String password,
                                     @RequestParam("fullname") String fullname, @RequestParam("roleId") int roleId) {
        LOGGER.info("Begin Registration in AccountWS with email - password - fullname: {}",
                email + " - " + password + " - " + fullname);
        int result;
        try {
            String status = "new";
            Date createdDate = new Date();
            String tokenString = tokenService.addAuthentication(email);
            UsersDTO usersDTO = new UsersDTO(0, email, password, fullname, status,
                    createdDate, createdDate, null, roleId, tokenString);
            result = userService.registration(usersDTO);
            LOGGER.info("End Registration in AccountWS with email - password - fullname: {}",
                    email + " - " + password + " - " + fullname);

            if (result > -1) {
                if(roleId == 2){

                }
                return new RestResponse(true, "Create To Successful", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To Account", null);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(value = "/checkLogin", produces = "application/json;charset=UTF-8")
    public RestResponse checkLogin(@RequestParam("accessToken") String accessToken) {
        LOGGER.info("Begin login in Account WS with Token : {}", accessToken);
        UsersDTO usersDTO;

        try {
            usersDTO = userService.findAccountByToken(accessToken);
            LOGGER.info("End login in Account WS with Token : ", accessToken);
            if (usersDTO != null) {
                return new RestResponse(true, "CheckLogin To Successful", usersDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "CheckLogin To Fail", null);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(value = "/changePassword", produces = "application/json;charset=UTF-8")
    public RestResponse changePassword(@RequestParam("id") int id, @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) {
        LOGGER.info("Begin changePassword in AccountWS with Account id {}" + id);
        int success;
        try {
            success = userService.changePassword(id, newPassword, oldPassword);
            if (success > 0) {
                return new RestResponse(true, "changePassword Successful with new password is " + newPassword, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changePassword in AccountWS with Account id {}" + id);
        return new RestResponse(false, "changePassword Fail", null);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(value = "/changeStatus", produces = "application/json;charset=UTF-8")
    public RestResponse changeStatus(@RequestParam("id") int id, @RequestParam("newStatus") String newStatus) {
        LOGGER.info("Begin changeStatus in AccountWS with Account id : {}" + id);
        int success;
        try {
            success = userService.changeStatus(id, newStatus);
            if (success > 0) {
                return new RestResponse(true, "changeStatus Successful with status " + newStatus, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Begin changeStatus in AccountWS with Account id : {}" + id);
        return new RestResponse(false, "changeStatus Fail", null);
    }

}
