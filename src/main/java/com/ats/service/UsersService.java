package com.ats.service;



import com.ats.dto.UsersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
	 UsersDTO login(String email, String password);

	 int registration(UsersDTO dto);
	
	 int changePassword(int id , String newPassword , String oldPassword);
	
	 int changeStatus(int id , String newStatus);

	boolean checkAccountValidation(String email);
	
	boolean checkPassword(String password,int id);

	 UsersDTO findAccountByEmail(String email);
	
	 UsersDTO findAccountByToken(String token);

//	 UsersDTO findAccountById(int id);

}
