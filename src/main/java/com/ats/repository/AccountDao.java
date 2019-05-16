package com.ats.repository;

import org.springframework.stereotype.Repository;

import com.ats.entity.Account;
import com.ats.repository.GenericDao;

@Repository
public interface AccountDao extends GenericDao<Account, Integer> {
	
	public Account registration(Account account);

	public Account findAccountByUsername(String username);

	public Account findAccountById(int id);
}
