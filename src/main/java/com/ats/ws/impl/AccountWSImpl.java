package com.ats.ws.impl;


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
//		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		String result = "start";
		try {
			accountDTO = new AccountDTO();
			accountDTO = accountService.login(username, password);
			if(accountDTO != null) {
				result = "success";
			}else {
				result = "false";
			}
			LOGGER.info("End login in Account WS with username - password : {}", username + " - " + password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String registratrion(String username, String password, int typeID, int status) {
		byte st = 1;
		AccountDTO accountDTO = new AccountDTO();
		System.out.println(username);
		System.out.println(password);
		System.out.println(typeID);
		System.out.println(status);
		accountDTO.setUsername(username);
		accountDTO.setPassword(password);
		accountDTO.setTypeId(1);
		accountDTO.setEnable(st);
		LOGGER.info("Begin registration with AccountDTO Id : " + accountDTO.getId());
		if (accountDTO != null) {
			try {
				int accountId = accountService.registration(accountDTO);
				
				LOGGER.info("End registration with AccountDTO Id : " + accountDTO.getUsername());
				// if (accountId > -1) {
				//// MemberDTO memberDTO = new MemberDTO();
				//// memberDTO.setAccountId(accountId);
				////// Date dob = new SimpleDateFormat("dd/MM/yyyy").parse("17/02/1997");
				//// memberDTO.setEmail(email);
				//// memberDTO.setTel(tel);
				//// memberDTO.setMoney(0);
				// LOGGER.info("Begin registration with MemberDTO Id : " +
				// memberDTO.getAccountId());
				// memberService.registration(memberDTO);
				// LOGGER.info("End registration with MemberDTO Id : " +
				// memberDTO.getAccountId());
				// status = "Successfull";
				// } else if (accountId == -1) {
				// status = "Existed";
				// }

			} catch (Exception e) {
				System.out.println(e);
				return "FAIL SML";
			}
		}
		return "success";
	}

}
