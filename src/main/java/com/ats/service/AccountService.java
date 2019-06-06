package com.ats.service;



import org.springframework.stereotype.Service;

import com.ats.dto.AccountDTO;

@Service
public interface AccountService {
	 AccountDTO login(String email, String password);

	 int registration(AccountDTO dto);
	
	 int changePassword(int id , String newPassword , String oldPassword);
	
	 int changeStatus(int id , String newStatus);

	boolean checkAccountValidation(String email);
	
	boolean checkPassword(String password,int id);

	 AccountDTO findAccountByEmail(String email);
	
	 AccountDTO findAccountByToken(String token);

//	 AccountDTO findAccountById(int id);
}
