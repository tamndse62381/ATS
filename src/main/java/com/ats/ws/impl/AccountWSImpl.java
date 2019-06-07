package com.ats.ws.impl;

import java.util.Date;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @ResponseBody
    public RestResponse Login(String email, String password) {

        LOGGER.info("Begin login in Account WS with username - password: {}", email + " - " + password);
        AccountDTO accountDTO = null;
        // HashMap<String, Integer> hm = new HashMap<String, Integer>();
        accountDTO = new AccountDTO();
        try {
            accountDTO = accountService.login(email, password);
            LOGGER.info("End login in Account WS with username - password : {}", email + " - " + password);
            if (accountDTO != null) {
                return new RestResponse(true, "Login Successful", accountDTO);
            } else {
                return new RestResponse(false, "Login Fail", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RestResponse login(String email, String password) {
        return null;
    }

    @Override
    public RestResponse registration(String email, String password, String fullname) {
        LOGGER.info("Begin Registration in AccountWS with email - password - fullname: {}",
                email + " - " + password + " - " + fullname);
        int result = 0;
        try {
            String status = "new";
            Date createdDate = new Date();
            String tokenString = tokenService.addAuthentication(email);

            AccountDTO accountDTO = new AccountDTO(0, email, password, fullname, status, createdDate, createdDate, null, 1,
                    tokenString);

            result = accountService.registration(accountDTO);
            LOGGER.info("End Registration in AccountWS with email - password - fullname: {}",
                    email + " - " + password + " - " + fullname);
            if (result > -1) {
                return new RestResponse(true, "Create To Successful", accountDTO);
            } else {

                return new RestResponse(false, "Fail Create To Account", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public RestResponse checkLogin(String accessToken) {
        LOGGER.info("Begin login in Account WS with Token : {}", accessToken);
        AccountDTO accountDTO = null;
        HashMap<Integer, AccountDTO> hm = null;


        // HashMap<Integer, AccountDTO> hm = null;

        try {
            accountDTO = accountService.findAccountByToken(accessToken);
            LOGGER.info("End login in Account WS with Token : ", accessToken);
            System.out.println(" Có null hay ko abcd " + accountDTO);
            if (accountDTO != null) {
                return new RestResponse(true, "CheckLogin To Successful", accountDTO);
            } else {
                return new RestResponse(false, "CheckLogin To Fail", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public RestResponse changePassword(int id, String oldPassword, String newPassword) {
        LOGGER.info("Begin changePassword in AccountWS with Account id {}" + id);
        int success = 0;
        try {
            success = accountService.changePassword(id, newPassword, oldPassword);
            if (success > 0) {
                return new RestResponse(true, "changePassword Successful with new password is " + newPassword, null);
            } else {
                return new RestResponse(false, "changePassword Fail", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("End changePassword in AccountWS with Account id {}" + id);
        return null;
    }

    @Override
    public RestResponse changeStatus(String accessToken) {
        // TODO Auto-generated method stub
        return null;
    }

}
