package com.ats.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.dto.AccountDTO;
import com.ats.service.AccountService;
import com.ats.repository.AccountDao;
import com.ats.transformer.AccountTransformer;

import com.ats.entity.Account;
import com.ats.service.impl.AccountServiceImpl;
import com.ats.util.EncrytedPasswordUtils;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountTransformer accountTransformer;
	
	private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

	private EncrytedPasswordUtils passwordUtil;
	
	@Override
	public AccountDTO login(String username, String password) {
		LOGGER.info("Begin login in Account Service with username - password: {}", username + " - " + password);
		AccountDTO accountDTO = null;
		passwordUtil = new EncrytedPasswordUtils();
		if (username != null) {
			accountDTO = findAccountByUsername(username);
			if (accountDTO != null) {
				if (passwordUtil.compare(password, accountDTO.getPassword())) {
					return accountDTO;
				} else {
					return null;
				}
			}
		}
		LOGGER.info("End login in Account Service with result: {}", accountDTO);
		return accountDTO;
	}

	@Override
	public int registration(AccountDTO dto) {
		LOGGER.info("Begin registration in Account Service with Account DTO ID : {}", dto.getId());
		Account newAccount = null;
		EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();

		String newPassword = passwordUtil.encrytePassword(dto.getPassword());
		dto.setPassword(newPassword);

		Account account = accountTransformer.convertToEntity(dto);

		AccountDTO existedAccount = null;
		existedAccount = findAccountByUsername(dto.getUsername());

		if (existedAccount == null) {
			if (account != null) {
				try {
					newAccount = accountDao.registration(account);
					LOGGER.info("End registration in Account Service with result: {}", newAccount.toString());

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			return -1;
		}
		return newAccount.getId();
	}

	@Override
	public boolean checkAccountValidation(String username) {
		boolean valid = true;

		if (accountDao.findAccountByUsername(username) != null) {
			valid = false;
		}

		return valid;
	}

	@Override
	public AccountDTO findAccountByUsername(String username) {
		LOGGER.info("Begin findAccountByUsername in Account Service with username{}", username);
		AccountDTO accountDTO = null;
		Account account = null;
		if (username != null) {
			account = accountDao.findAccountByUsername(username);
			if (account != null) {
				accountDTO = accountTransformer.convertToDTO(account);
			}
		}
		LOGGER.info("End findAccountByUserName in Account Service with result: {}", accountDTO);
		return accountDTO;
	}

	@Override
	public AccountDTO findAccountById(int id) {
		LOGGER.info("Begin findAccountById in Account Service with id ", +id);
		AccountDTO accountDTO = null;
		Account account = null;

		account = accountDao.findAccountById(id);
		if (account != null) {
			accountDTO = accountTransformer.convertToDTO(account);
		}

		LOGGER.info("Begin findAccountById in Account Service with id ", +id);
		return accountDTO;
	}

}
