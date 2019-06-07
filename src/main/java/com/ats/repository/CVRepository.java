package com.ats.repository;

import com.ats.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CVRepository extends JpaRepository<Cv, Integer> {
    @Query("Select c from Cv c where c.account.email = :id")
    public List<Cv> findAccountByEmail(@Param("id") int id);
}
