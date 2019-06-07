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

    @Query("Select b from Account b where b.Email = :email")
    Account findAccountByEmail(@Param("email") String email);

    @Query("Select b from Account b where b.AccessToken = :accessToken")
    Account findAccountByToken(@Param("accessToken") String token);

    @Transactional
    @Modifying
    @Query("UPDATE Account b SET b.LastLogin = :lastDate WHERE b.Email = :email and  b.AccessToken = :accessToken")
    void editAccountLastLogin(@Param("lastDate") Date date, @Param("email") String email,
                              @Param("accessToken") String accessToken);

    @Transactional
    @Modifying
    @Query("UPDATE Account b SET b.Password = :newPassword WHERE b.ID = :id")
    int changePassword(@Param("id") int id, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE Account b SET b.Status = :newStatus WHERE b.ID = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);
}
