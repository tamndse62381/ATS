package com.ats.repository;

import com.ats.entity.Countcv;
import com.ats.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CVRepository extends JpaRepository<Cv, Integer> {
    @Query("Select c from Cv c where c.email = :email")
     Cv findCVByEmail (@Param("email") String email);

    @Query("select c from Cv c where c.userId = :userid and c.isActive = :status")
    List<Cv> checkIsActive(@Param("userid") Integer userid, @Param("status") Integer status);

    Optional<Cv> findById(int id);

    List<Cv> findByUserIdAndStatus(int id, String status);

    List<Cv> findByUserId(int id);
}
