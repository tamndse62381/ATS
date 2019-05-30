package com.ats.ws.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ws.AccountWS;
import com.ats.dto.AccountDTO;

import com.ats.service.AccountService;

@RestController
public class AccountWSImpl implements AccountWS {

	@Autowired
	AccountService accountService;

	private static final Logger LOGGER = LogManager.getLogger(AccountWSImpl.class);

	@Override
	public String checkLogin(String username, String password) {
		LOGGER.info("Begin login in Account WS with username - password: {}", username + " - " + password);
		AccountDTO accountDTO = null;
		// HashMap<String, Integer> hm = new HashMap<String, Integer>();
		String result = "start";
		try {
			accountDTO = new AccountDTO();
			accountDTO = accountService.login(username, password);
			if (accountDTO != null) {
				result = "success";
			} else {
				result = "false";
			}
			LOGGER.info("End login in Account WS with username - password : {}", username + " - " + password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String registratrion(String email, String password, String fullname) {
		LOGGER.info("Begin Registration in AccountWS with email - password - fullname: {}",
				email + " - " + password + " - " + fullname);
		String status = "new";
		int id = 1;
		Date createdDate = new Date();
		Date lastLogin = null;
		Date lastModify = null;
		int roleId = 1;
		AccountDTO accountDTO = new AccountDTO(id, email, password, fullname, status, createdDate, lastLogin, lastModify, roleId);
		accountService.registration(accountDTO);
		LOGGER.info("End Registration in AccountWS with email - password - fullname: {}",
				email + " - " + password + " - " + fullname);
		return null;
	}

}
