package com.ats.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

	@Query("Select b from Account b where b.email = :email")
	public Account findAccountByEmail(@Param("email") String email);

	
	@Transactional
	@Modifying
	@Query("UPDATE Account b SET b.lastLogin = :lastDate WHERE b.email = :email and  b.accessToken = :accessToken")
	public void editAccountLastLogin(@Param("lastDate") Date date, @Param("email") String email,
			@Param("accessToken") String accessToken);
	
	@Transactional
	@Modifying
	@Query("UPDATE Account b SET b.password = :newPassword WHERE b.id = :id")
	public int changePassword(@Param("id") int id, @Param("newPassword") String newPassword);
}
