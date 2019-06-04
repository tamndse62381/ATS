package com.ats.service.impl;

import java.util.Date;
//import java.util.HashMap;

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
import com.ats.token.TokenAuthenticationService;
import com.ats.util.EncrytedPasswordUtils;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountTransformer accountTransformer;

	@Autowired
	TokenAuthenticationService tokenService;
	private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

	private EncrytedPasswordUtils passwordUtil;

	@Override
	public AccountDTO login(String email, String password) {
		LOGGER.info("Begin login in Account Service with email - password: {}", email + " - " + password);
		AccountDTO accountDTO = null;
		AccountDTO reTurnAccountDTO = null;
		passwordUtil = new EncrytedPasswordUtils();
		if (email != null) {
			accountDTO = findAccountByEmail(email);
			if (accountDTO != null) {
				if (passwordUtil.compare(password, accountDTO.getPassword())) {
					Date lastLoginDate = new Date();

					accountDao.editAccountLastLogin(lastLoginDate, accountDTO.getEmail(), accountDTO.getAccessToken());

					reTurnAccountDTO = new AccountDTO(accountDTO.getId(), accountDTO.getFullname(),
							accountDTO.getEmail(), accountDTO.getRoleId(),accountDTO.getAccessToken());
					return reTurnAccountDTO;
				} else {
					return null;
				}
			}
		}
		LOGGER.info("End login in Account Service with result: {}", accountDTO);
		return reTurnAccountDTO;
	}

	@Override
	public int registration(AccountDTO dto) {
		LOGGER.info("Begin registration in Account Service with Account DTO Email : {}", dto.getEmail());
		Account newAccount = null;
		EncrytedPasswordUtils passwordUtil = new EncrytedPasswordUtils();

		String newPassword = passwordUtil.encrytePassword(dto.getPassword());
		dto.setPassword(newPassword);

		Account account = accountTransformer.convertToEntity(dto);

		AccountDTO existedAccount = null;
		existedAccount = findAccountByEmail(dto.getEmail());

		if (existedAccount == null) {
			if (account != null) {
				try {
					newAccount = accountDao.save(account);
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
	public boolean checkAccountValidation(String email) {
		boolean valid = true;

		if (accountDao.findAccountByEmail(email) != null) {
			valid = false;
		}

		return valid;
	}

	@Override
	public AccountDTO findAccountByEmail(String email) {
		LOGGER.info("Begin findAccountByEmail in Account Service with email {}", email);
		AccountDTO accountDTO = null;
		Account account = null;
		if (email != null) {
			account = accountDao.findAccountByEmail(email);

			if (account != null) {
				accountDTO = accountTransformer.convertToDTO(account);
			}
		}
		LOGGER.info("End findAccountByEmail in Account Service with result: {}", accountDTO);
		return accountDTO;
	}

	@Override
	public AccountDTO findAccountById(int id) {
		LOGGER.info("Begin findAccountById in Account Service with id ", +id);
		AccountDTO accountDTO = null;
		Account account = null;

		account = accountDao.findOne(id);
		if (account != null) {
			accountDTO = accountTransformer.convertToDTO(account);
		}

		LOGGER.info("Begin findAccountById in Account Service with id ", +id);
		return accountDTO;
	}

	@Override
	public AccountDTO findAccountByToken(String token) {
		LOGGER.info("Begin findAccountByToken in Account Service with token {}", token);
		AccountDTO accountDTO = null;
		Account account = null;
		AccountDTO reTurnAccountDTO = null;
		int i = 0;
		// HashMap<Integer, AccountDTO> hm = new HashMap<Integer, AccountDTO>();
		if (token != null) {
			account = accountDao.findAccountByToken(token);
			System.out.println(" Có null hay ko abc " + account);
			if (account != null) {
				System.out.println("I'm here");
				Date nowDate = new Date();
				i = nowDate.compareTo(account.getLastLogin());
				System.out.println("Ngày hiện tại khác ngày Login : " + i);
				if (i > 10) {
					return null;
				} else {
					accountDTO = accountTransformer.convertToDTO(account);
					reTurnAccountDTO = new AccountDTO(accountDTO.getId(), accountDTO.getFullname(),
							accountDTO.getEmail(), accountDTO.getRoleId(),accountDTO.getAccessToken());
					LOGGER.info("End findAccountByToken in Account Service with token: {}",
							reTurnAccountDTO.getAccessToken());
				}
			} else {
				return null;
			}
		}
		return reTurnAccountDTO;
	}

	@Override
	public int update(AccountDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeStatus(AccountDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
}
