package com.ats.ws.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ws.AccountWS;
import com.ats.dto.AccountDTO;
import com.ats.entity.Account;
import com.ats.service.AccountService;
import com.ats.token.TokenAuthenticationService;

@RestController
public class AccountWSImpl implements AccountWS {

	@Autowired
	AccountService accountService;
	@Autowired
	TokenAuthenticationService tokenService;
	private static final Logger LOGGER = LogManager.getLogger(AccountWSImpl.class);

	@Override
	@ResponseBody
	public AccountDTO checkLogin(String email, String password) throws JSONException{
		LOGGER.info("Begin login in Account WS with username - password: {}", email + " - " + password);
		AccountDTO accountDTO = null;
		// HashMap<String, Integer> hm = new HashMap<String, Integer>();
		accountDTO = new AccountDTO();
		try {
			accountDTO = accountService.login(email, password);
			if (accountDTO != null) {	
				//Khi Login thành công
			} else {
				//Khi login thất bại
			}
			LOGGER.info("End login in Account WS with username - password : {}", email + " - " + password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountDTO;
	}

	@Override
	public String registratrion(String email, String password, String fullname) {
		LOGGER.info("Begin Registration in AccountWS with email - password - fullname: {}",
				email + " - " + password + " - " + fullname);
		try {
			String status = "new";
			Date createdDate = new Date();			
			String tokenString = tokenService.addAuthentication(email);
			System.out.println("Result : " + tokenString);
			AccountDTO accountDTO = new AccountDTO(email, password, fullname, status, createdDate, null, null, 1,
					tokenString);
			accountService.registration(accountDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("End Registration in AccountWS with email - password - fullname: {}",
				email + " - " + password + " - " + fullname);
		return null;
	}

}
