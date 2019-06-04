package com.ats.service;



import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;

@Service
public interface AccountService {
	public AccountDTO login(String email, String password);

	public int registration(AccountDTO dto);
	
	public int update(AccountDTO dto);
	
	public int changeStatus(AccountDTO dto);

	boolean checkAccountValidation(String email);

	public AccountDTO findAccountByEmail(String email);
	
	public AccountDTO findAccountByToken(String token);

	public AccountDTO findAccountById(int id);
}
