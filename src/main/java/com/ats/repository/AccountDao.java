package com.ats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.entity.Account;


@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

	@Query("Select b from Account b where b.email = :email")
	public Account findAccountByEmail(@Param("email") String email);

}
