package com.ats.repository;

import java.util.Date;

import com.ats.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("Select b from Users b where b.email = :email")
    Users findAccountByEmail(@Param("email") String email);

    @Query("Select b from Users b where b.accessToken = :accessToken")
    Users findAccountByToken(@Param("accessToken") String token);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.LastLogin = :lastDate WHERE b.email = :email and  b.accessToken = :accessToken")
    void editAccountLastLogin(@Param("lastDate") Date date, @Param("email") String email,
                              @Param("accessToken") String accessToken);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.password = :newPassword WHERE b.id = :id")
    int changePassword(@Param("id") int id, @Param("newPassword") String newPassword);

    @Transactional
    @Modifying
    @Query("UPDATE Users b SET b.Status = :newStatus WHERE b.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);
}
