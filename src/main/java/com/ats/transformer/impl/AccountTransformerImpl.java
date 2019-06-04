package com.ats.transformer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;
import com.ats.entity.Account;
import com.ats.service.RoleService;
import com.ats.transformer.AccountTransformer;
import com.ats.transformer.RoleTransformer;
import com.ats.transformer.impl.AccountTransformerImpl;

@Service
public class AccountTransformerImpl implements AccountTransformer {

	private static final Logger LOGGER = LogManager.getLogger(AccountTransformerImpl.class);
	
	@Autowired
	RoleService roleService;
	@Autowired
	RoleTransformer roleTransformer;
	@Override
	public Account convertToEntity(AccountDTO dto) {
		LOGGER.info("Begin convertToEntity with Account Entity ID: {}");
		Account account = null;
		
		if (dto != null) {
			account = new Account();
			
			account.setEmail(dto.getEmail());
			account.setPassword(dto.getPassword());
			account.setCreatedDate(dto.getCreatedDate());
			account.setFullName(dto.getFullname());
			account.setLastLogin(dto.getLastLogin());
			System.out.println(dto.getRoleId());
			account.setRole(roleTransformer.convertDTOToEntity(roleService.findRoleById(dto.getRoleId())));
			account.setStatus(dto.getStatus());
			account.setAccessToken(dto.getAccessToken());
		}
		LOGGER.info("End convertToEntity with result: {}", account.toString());
		return account;
	}

	@Override
	public AccountDTO convertToDTO(Account account) {
		LOGGER.info("Begin convertToDTO with Account Entity: {}", account.toString());
		AccountDTO accountDTO = null;

		if (account != null) {	
			accountDTO = new AccountDTO();
			accountDTO.setId(account.getId());
			accountDTO.setEmail(account.getEmail());
			accountDTO.setPassword(account.getPassword());
			accountDTO.setCreatedDate(account.getCreatedDate());
			accountDTO.setFullname(account.getFullName());
			accountDTO.setLastLogin(account.getLastLogin());
			accountDTO.setRoleId(account.getRole().getId());
			accountDTO.setStatus(account.getStatus());
			accountDTO.setAccessToken(account.getAccessToken());
			
		}
		LOGGER.info("End convertToDTO with result: {}", accountDTO.toString());
		return accountDTO;
	}

}
