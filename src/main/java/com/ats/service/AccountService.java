package com.ats.service;



import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;

@Service
public interface AccountService {
	public AccountDTO login(String email, String password);

	public int registration(AccountDTO dto);
	
	public int changePassword(int id , String newPassword , String oldPassword);
	
	public int changeStatus(AccountDTO dto);

	boolean checkAccountValidation(String email);
	
	boolean checkPassword(String password,int id);

	public AccountDTO findAccountByEmail(String email);
	
	public AccountDTO findAccountByToken(String token);

	public AccountDTO findAccountById(int id);
}
