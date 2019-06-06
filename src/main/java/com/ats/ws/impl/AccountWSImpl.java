package com.ats.ws.impl;

import java.util.Date;
//import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ws.AccountWS;

import com.ats.dto.AccountDTO;

import com.ats.service.AccountService;
import com.ats.token.TokenAuthenticationService;
import com.ats.util.RestResponse;

@RestController
public class AccountWSImpl implements AccountWS {

    @Autowired
    AccountService accountService;
    @Autowired
    TokenAuthenticationService tokenService;
    private static final Logger LOGGER = LogManager.getLogger(AccountWSImpl.class);

    @Override
    @ResponseBody
    public RestResponse login(String email, String password) {
        LOGGER.info("Begin login in Account WS with username - password: {}", email + " - " + password);
        AccountDTO accountDTO ;
        accountDTO = new AccountDTO();
        try {
            accountDTO = accountService.login(email, password);
            LOGGER.info("End login in Account WS with username - password : {}", email + " - " + password);
            if (accountDTO != null) {
                return new RestResponse(true, "Login Successful", accountDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Login Fail", null);
    }

    @Override
    public RestResponse registration(String email, String password, String fullname) {
        LOGGER.info("Begin Registration in AccountWS with email - password - fullname: {}",
                email + " - " + password + " - " + fullname);
        int result ;
        try {
            String status = "new";
            Date createdDate = new Date();
            String tokenString = tokenService.addAuthentication(email);
            AccountDTO accountDTO = new AccountDTO(0, email, password, fullname, status, createdDate, createdDate, null,
                    1, tokenString);
            result = accountService.registration(accountDTO);
            LOGGER.info("End Registration in AccountWS with email - password - fullname: {}",
                    email + " - " + password + " - " + fullname);
            if (result > -1) {
                return new RestResponse(true, "Create To Successful", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "Fail Create To Account", null);
    }

    @Override
    public RestResponse checkLogin(String accessToken) {
        LOGGER.info("Begin login in Account WS with Token : {}", accessToken);
        AccountDTO accountDTO;

        try {
            accountDTO = accountService.findAccountByToken(accessToken);
            LOGGER.info("End login in Account WS with Token : ", accessToken);
            if (accountDTO != null) {
                return new RestResponse(true, "CheckLogin To Successful", accountDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResponse(false, "CheckLogin To Fail", null);
    }

    @Override
    public RestResponse changePassword(int id, String oldPassword, String newPassword) {
        LOGGER.info("Begin changePassword in AccountWS with Account id {}" + id);
        int success;
        try {
            success = accountService.changePassword(id, newPassword, oldPassword);
            if (success > 0) {
                return new RestResponse(true, "changePassword Successful with new password is " + newPassword, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changePassword in AccountWS with Account id {}" + id);
        return new RestResponse(false, "changePassword Fail", null);
    }

    @Override
    public RestResponse changeStatus(int id, String newStatus) {
        LOGGER.info("Begin changeStatus in AccountWS with Account id : {}" + id);
        int success;
        try {
            success = accountService.changeStatus(id, newStatus);
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
