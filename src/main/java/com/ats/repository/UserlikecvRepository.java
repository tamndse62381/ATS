package com.ats.repository;

import com.ats.entity.Userslikecv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserlikecvRepository extends JpaRepository<Userslikecv, Integer> {
    @Query("Select U from Userslikecv u where u.userId = :employerid and u.cvid = :cvid")
    List<Userslikecv> check(@Param("userid") int userid, @Param("cvid") int cvid);

    List<Userslikecv> findUserslikecvsByUserId(int EmployerId);
}
