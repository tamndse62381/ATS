package com.ats.service;

import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;

@Service
public interface AccountService {
	public AccountDTO login(String username, String password);

	public int registration(AccountDTO dto);

	boolean checkAccountValidation(String username);

	public AccountDTO findAccountByUsername(String username);

	public AccountDTO findAccountById(int id);
}
