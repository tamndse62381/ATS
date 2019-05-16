package com.ats.transformer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;
import com.ats.entity.Account;
import com.ats.transformer.AccountTransformer;
import com.ats.transformer.impl.AccountTransformerImpl;

@Service
public class AccountTransformerImpl implements AccountTransformer {

	private static final Logger LOGGER = LogManager.getLogger(AccountTransformerImpl.class);
	
	@Override
	public Account convertToEntity(AccountDTO dto) {
		LOGGER.info("Begin convertToEntity with Account Entity ID: {}", dto.getId());
		Account account = null;
		byte status = 1;
		if (dto != null) {
			account = new Account();
			account.setId(dto.getId());
			account.setUsername(dto.getUsername());
			account.setPassword(dto.getPassword());
			account.setTypeAccount(1);
	
		}
		LOGGER.info("End convertToEntity with result: {}", account.toString());
		return account;
	}

	@Override
	public AccountDTO convertToDTO(Account account) {
		LOGGER.info("Begin convertToDTO with User Entity: {}", account.toString());
		AccountDTO accountDTO = null;
		byte status = 1;
		if (account != null) {
			accountDTO = new AccountDTO();
			accountDTO.setId(account.getId());
			accountDTO.setUsername(account.getUsername());
			accountDTO.setPassword(account.getPassword());
			accountDTO.setTypeId(1);

		}
		LOGGER.info("End convertToDTO with result: {}", accountDTO.toString());
		return accountDTO;
	}

}
