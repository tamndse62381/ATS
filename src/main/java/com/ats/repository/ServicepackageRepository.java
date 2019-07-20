package com.ats.repository;

import com.ats.entity.Servicepackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServicepackageRepository extends JpaRepository<Servicepackage,Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Servicepackage b SET b.status = :newStatus WHERE b.id = :id")
    int changeStatus(@Param("id") int id, @Param("newStatus") String newStatus);
}
