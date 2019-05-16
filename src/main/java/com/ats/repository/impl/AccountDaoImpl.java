package com.ats.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ats.entity.Account;
import com.ats.repository.AccountDao;
import com.ats.repository.impl.GenericDaoImpl;
import com.ats.repository.impl.AccountDaoImpl;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, Integer> implements AccountDao {

	private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);
	

	@Override
	public Account registration(Account account) {
		LOGGER.info("Begin registration in Account DAO with Account: {}", account.getUsername());
		Account newAccount = null;
		System.out.println(account.getPassword());
		if (account != null) {
			newAccount = this.create(account);
		}
		LOGGER.info("End registration in Account DAO with Account have id: {}", newAccount.getId());
		return newAccount;
	}

	@Override
	public Account findAccountByUsername(String username) {
		LOGGER.info("Begin findAccountByUsername in Account DAO with username: {}", username);
		Account account = null;
		if (username != null) {
			List<Account> listAccount = new ArrayList<Account>();
			String sql = "Select b from " + Account.class.getName() + " As b where b.username =:sUsername";

			Query query = this.entitymanager.createQuery(sql);
			query.setParameter("sUsername", username);
			listAccount = query.getResultList();
			if (listAccount.size() > 0) {
				account = listAccount.get(0);
			}
		}
		LOGGER.info("End findAccountByUsername in Account DAO with result: {}", account);
		return account;
	}

	@Override
	public Account findAccountById(int id) {
		LOGGER.info("Begin findAccountById in Account DAO with id : ", id);
		Account account = null;

		List<Account> listAccount = new ArrayList<Account>();
		String sql = "Select b from " + Account.class.getName() + " As b where b.id =:sId";

		Query query = this.entitymanager.createQuery(sql);
		query.setParameter("sId", id);
		listAccount = query.getResultList();
		if (listAccount.size() > 0) {
			account = listAccount.get(0);

		}
		LOGGER.info("End findAccountById in Account DAO with id : ", id);
		return account;
	}

}
