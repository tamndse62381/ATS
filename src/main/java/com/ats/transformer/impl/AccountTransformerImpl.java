package com.ats.transformer.impl;

import com.ats.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;
import com.ats.service.RoleService;
import com.ats.transformer.AccountTransformer;
import com.ats.transformer.RoleTransformer;

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
		ModelMapper modelMapper = new ModelMapper();
		if (dto != null) {
			account = modelMapper.map(dto, Account.class);
			// account.setEmail(dto.getEmail());
			// account.setPassword(dto.getPassword());
			// account.setCreatedDate(dto.getCreatedDate());
			// account.setFullName(dto.getFullname());
			// account.setLastLogin(dto.getLastLogin());
			// account.setRole(roleTransformer.convertDTOToEntity(roleService.findRoleById(dto.getRoleId())));
			// account.setStatus(dto.getStatus());
			// account.setAccessToken(dto.getAccessToken());
		}
		LOGGER.info("End convertToEntity with result: {}", account.toString());
		return account;
	}

	@Override
	public AccountDTO convertToDTO(Account account) {
		LOGGER.info("Begin convertToDTO with Account Entity: {}", account.toString());
		AccountDTO accountDTO = null;
		ModelMapper modelMapper = new ModelMapper();
		if (account != null) {
			accountDTO = modelMapper.map(account, AccountDTO.class);	
//			accountDTO.setId(account.getId());
//			accountDTO.setEmail(account.getEmail());
//			accountDTO.setPassword(account.getPassword());
//			accountDTO.setCreatedDate(account.getCreatedDate());
//			accountDTO.setFullname(account.getFullName());
//			accountDTO.setLastLogin(account.getLastLogin());
//			accountDTO.setRoleId(account.getRole().getId());
//			accountDTO.setStatus(account.getStatus());
//			accountDTO.setAccessToken(account.getAccessToken());

		}
		LOGGER.info("End convertToDTO with result: {}", accountDTO.toString());
		return accountDTO;
	}

}
